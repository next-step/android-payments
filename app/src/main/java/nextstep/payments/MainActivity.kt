package nextstep.payments

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import nextstep.payments.ui.screen.creditcard.CreditCardRoute
import nextstep.payments.ui.screen.creditcard.CreditCardViewModel
import nextstep.payments.ui.screen.editcard.EditCardActivity
import nextstep.payments.ui.screen.newcard.NewCardActivity
import nextstep.payments.ui.theme.PaymentsTheme

class MainActivity : ComponentActivity() {

    private val viewModel: CreditCardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == RESULT_OK) {
                    viewModel.fetchCards()
                }
            }
            PaymentsTheme {
                CreditCardRoute(
                    viewModel = viewModel,
                    onCardClick = {
                        val intent = Intent(this, EditCardActivity::class.java).apply {
                            putExtra("CARD_ID", it)
                        }
                        launcher.launch(intent)
                    },
                ) {
                    val intent = Intent(this, NewCardActivity::class.java)
                    launcher.launch(intent)
                }
            }
        }
    }
}
