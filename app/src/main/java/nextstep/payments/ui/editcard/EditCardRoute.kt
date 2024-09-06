package nextstep.payments.ui.editcard

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
import androidx.activity.viewModels
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nextstep.payments.R
import nextstep.payments.model.card.CardNumber
import nextstep.payments.model.card.CreditCard
import nextstep.payments.repository.PaymentCardsRepository
import nextstep.payments.ui.component.card.CardBankInformation
import nextstep.payments.ui.component.card.CardInformation
import nextstep.payments.ui.theme.PaymentsTheme
import java.time.YearMonth
import java.time.format.DateTimeFormatter

internal object EditCardRoute {
    /**
     * `PaymentCardsRepository`에게 카드 업데이트를 요청하기 위해선 원본 카드 객체를 알아야 합니다.
     * */
    private lateinit var targetCard: CreditCard

    fun startActivity(
        targetCard: CreditCard,
        context: Context,
        launcher: ManagedActivityResultLauncher<Intent, ActivityResult>
    ) {
        this.targetCard = targetCard

        val intent = Intent(context, EditCardActivity::class.java)
        launcher.launch(intent)
    }

    class EditCardViewModel(
        private val repository: PaymentCardsRepository = PaymentCardsRepository
    ) : ViewModel() {
        private val _uiState = MutableStateFlow(targetCard.toUi())
        val uiState = _uiState.asStateFlow()

        // 카드를 성공적으로 업데이트했는지 여부를 나타내는 상태.
        private val _cardUpdated = MutableSharedFlow<Boolean>()
        val cardUpdated = _cardUpdated.asSharedFlow()

        private val _errorFlow = MutableSharedFlow<Throwable>()
        val errorFlow = _errorFlow.asSharedFlow()

        fun setCardNumber(cardNumber: String) {
            if (cardNumber.length > 16) return
            _uiState.update { it.copy(cardNumber = cardNumber.filter(Char::isDigit)) }
        }

        fun setExpiredDate(expirationDate: String) {
            if (expirationDate.length > 4) return
            _uiState.update { it.copy(expirationDate = expirationDate.filter(Char::isDigit)) }
        }

        fun setOwnerName(ownerName: String) {
            _uiState.update { it.copy(ownerName = ownerName) }
        }

        fun setPassword(password: String) {
            if (password.length > 4) return
            _uiState.update { it.copy(password = password.filter(Char::isDigit)) }
        }

        fun updateCard() {
            val currentUiState = _uiState.value
            runCatching {
                val cardNumbers = currentUiState.cardNumber.chunked(4).map { CardNumber(it) }
                val expirationDate =
                    YearMonth.parse(
                        currentUiState.expirationDate,
                        DateTimeFormatter.ofPattern("MMyy")
                    )

                CreditCard(
                    id = targetCard.id,
                    cardNumbers = cardNumbers,
                    expirationDate = expirationDate,
                    password = currentUiState.password,
                    ownerName = currentUiState.ownerName,
                    bankType = currentUiState.bank.bankType
                )
            }.onSuccess { updatedCard ->
                repository.updateCard(targetCard, updatedCard)
                viewModelScope.launch { _cardUpdated.emit(true) }
            }.onFailure { e ->
                Log.e("EditCard", "Error edit card: ${e.message}")
                viewModelScope.launch { _errorFlow.emit(e) }
            }
        }
    }

    internal class EditCardActivity : ComponentActivity() {
        private val viewModel: EditCardViewModel by viewModels()

        @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            setContent {
                val uiState by viewModel.uiState.collectAsStateWithLifecycle()
                val context = LocalContext.current
                val targetCardInformation = targetCard.toCardInformation()
                val snackbarHostState = remember { SnackbarHostState() }

                LaunchedEffect(true) {
                    viewModel.errorFlow.collectLatest {
                        snackbarHostState.showSnackbar(message = context.getString(R.string.card_edit_error))
                    }
                }
                LaunchedEffect(true) {
                    viewModel.cardUpdated.collectLatest {
                        snackbarHostState.showSnackbar(
                            message = context.getString(R.string.card_edit_success),
                            duration = SnackbarDuration.Short
                        )
                        setResult(RESULT_OK)
                        finish()
                    }
                }

                PaymentsTheme {
                    Scaffold(
                        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
                        content = {
                            EditCardScreen(
                                cardNumber = uiState.cardNumber,
                                expirationDate = uiState.expirationDate,
                                ownerName = uiState.ownerName,
                                password = uiState.password,
                                cardInformation = targetCardInformation,
                                setCardNumber = viewModel::setCardNumber,
                                setExpiredDate = viewModel::setExpiredDate,
                                setOwnerName = viewModel::setOwnerName,
                                setPassword = viewModel::setPassword,
                                onBackClick = { finish() },
                                onSaveClick = viewModel::updateCard,
                                modifier = Modifier,
                            )
                        }
                    )
                }
            }
        }
    }
}

private fun CreditCard.toCardInformation(): CardInformation = CardInformation(
    id = this.id,
    numberFirst = this.cardNumbers[0],
    numberSecond = this.cardNumbers[1],
    ownerName = this.ownerName,
    expirationDate = this.expirationDate,
    bank = CardBankInformation.from(this.bankType)
)

private fun CreditCard.toUi(): EditCardUiState {
    val formatter = DateTimeFormatter.ofPattern("MMyy")
    val formattedDate = expirationDate.format(formatter)

    return EditCardUiState(
        cardNumber = this.cardNumbers.joinToString(separator = "") { it.number },
        ownerName = this.ownerName,
        expirationDate = formattedDate,
        password = password,
        bank = CardBankInformation.from(bankType)
    )
}