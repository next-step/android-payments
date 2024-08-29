package nextstep.payments.newcard

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
            val cardNumber by viewModel.cardNumber.collectAsStateWithLifecycle()
            val expiredDate by viewModel.expiredDate.collectAsStateWithLifecycle()
            val ownerName by viewModel.ownerName.collectAsStateWithLifecycle()
            val password by viewModel.password.collectAsStateWithLifecycle()

            val snackbarHostState = remember { SnackbarHostState() }
            val context = LocalContext.current

            LaunchedEffect(true) {
                viewModel.errorFlow.collectLatest {
                    snackbarHostState.showSnackbar(message = context.getString(R.string.credit_card_error))
                }
            }

            PaymentsTheme {
                Scaffold(
                    snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
                ) {
                    NewCardScreen(
                        cardNumber = cardNumber,
                        expiredDate = expiredDate,
                        ownerName = ownerName,
                        password = password,
                        setCardNumber = viewModel::setCardNumber,
                        setExpiredDate = viewModel::setExpiredDate,
                        setOwnerName = viewModel::setOwnerName,
                        setPassword = viewModel::setPassword,
                        onAddCardClick = {
                            viewModel.addCard()
                            setResult(RESULT_OK)
                            finish()
                        },
                        onBackClick = {
                            finish()
                        }
                    )
                }
            }
        }
    }
}