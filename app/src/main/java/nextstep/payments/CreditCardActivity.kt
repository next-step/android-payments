package nextstep.payments

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
import nextstep.payments.creditcard.CreditCardScreen
import nextstep.payments.ui.theme.PaymentsTheme

class CreditCardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PaymentsTheme {
                CreditCardScreen(
                    onNavigateToNewCard = { launcher -> navigateToNewCard(launcher) })
            }
        }
    }


    private fun navigateToNewCard(launcher: ManagedActivityResultLauncher<Intent, ActivityResult>) {
        val intent = Intent(this, NewCardActivity::class.java)
        launcher.launch(intent)
    }
}

