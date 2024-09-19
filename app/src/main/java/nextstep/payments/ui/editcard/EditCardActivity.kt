package nextstep.payments.ui.editcard

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
import androidx.activity.viewModels
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import nextstep.payments.R
import nextstep.payments.model.card.CreditCard
import nextstep.payments.ui.component.card.CardBankInformation
import nextstep.payments.ui.component.card.CardInformation
import nextstep.payments.ui.theme.PaymentsTheme


internal class EditCardActivity : ComponentActivity() {
    private val viewModel: EditCardViewModel by viewModels { EditCardViewModel.Factory }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            val context = LocalContext.current
            val targetCardInformation = viewModel.targetCard.toCardInformation()
            val snackbarHostState = remember { SnackbarHostState() }

            LaunchedEffect(true) {
                viewModel.errorFlow.collectLatest {
                    snackbarHostState.showSnackbar(message = context.getString(R.string.card_edit_error))
                }
            }
            LaunchedEffect(true) {
                viewModel.cardUpdated.collectLatest {
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

    companion object {
        const val EXTRA_KEY = "edit_card"

        fun startActivity(
            targetCard: CreditCard,
            context: Context,
            launcher: ManagedActivityResultLauncher<Intent, ActivityResult>
        ) {
            val intent = Intent(context, EditCardActivity::class.java)
            intent.putExtra(EXTRA_KEY, targetCard)
            launcher.launch(intent)
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