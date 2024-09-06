package nextstep.payments.ui.newcard

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import nextstep.payments.R
import nextstep.payments.ui.theme.PaymentsTheme

class NewCardActivity : ComponentActivity() {
    private val viewModel: NewCardViewModel by viewModels()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            val context = LocalContext.current

            val snackbarHostState = remember { SnackbarHostState() }
            val focusManager = LocalFocusManager.current

            LaunchedEffect(true) {
                viewModel.errorFlow.collectLatest {
                    snackbarHostState.showSnackbar(message = context.getString(R.string.credit_card_error))
                }
            }
            LaunchedEffect(true) {
                viewModel.cardAdded.collectLatest {
                    setResult(RESULT_OK)
                    finish()
                }
            }
            LaunchedEffect(true) {
                viewModel.commandFocus.collectLatest {
                    focusManager.moveFocus(it)
                }
            }

            PaymentsTheme {
                Scaffold(
                    snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
                ) {
                    NewCardScreen(
                        cardNumber = uiState.cardNumber,
                        expiredDate = uiState.expirationDate,
                        ownerName = uiState.ownerName,
                        password = uiState.password,
                        bank = uiState.selectedBank,
                        isShowBanks = uiState.isShowBanks,
                        setCardNumber = viewModel::setCardNumber,
                        setExpiredDate = viewModel::setExpiredDate,
                        setOwnerName = viewModel::setOwnerName,
                        setPassword = viewModel::setPassword,
                        onAddCardClick = {
                            viewModel.addCard()
                        },
                        onBackClick = {
                            finish()
                        },
                        onBankClick = viewModel::setBank
                    )
                }
            }
        }
    }
}