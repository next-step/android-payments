package nextstep.payments

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import nextstep.payments.creditcard.CreditCardScreen
import nextstep.payments.creditcard.CreditCardViewModel
import nextstep.payments.ui.theme.PaymentsTheme

class CreditCardActivity : ComponentActivity() {
    private val viewModel: CreditCardViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val launcher =
                rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                    if (it.resultCode == RESULT_OK) {
                        viewModel.getCards()
                    }
                }
            PaymentsTheme {
                CreditCardScreen(onNavigateToNewCard = { navigateToNewCard(launcher) })
            }
        }
    }


    private fun navigateToNewCard(launcher: ManagedActivityResultLauncher<Intent, ActivityResult>) {
        val intent = Intent(this, NewCardActivity::class.java)
        launcher.launch(intent)
    }
}

