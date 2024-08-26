package nextstep.payments

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import nextstep.payments.screen.CardListScreen
import nextstep.payments.screen.newcard.NewCardActivity
import nextstep.payments.ui.theme.PaymentsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {

            }
            PaymentsTheme {
                CardListScreen(
                    navigateToNewCard = {
                        launcher.launch(Intent(this, NewCardActivity::class.java))
                    }
                )
            }
        }
    }
}
