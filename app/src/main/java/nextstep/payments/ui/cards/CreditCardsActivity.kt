package nextstep.payments.ui.cards

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import nextstep.payments.ui.newcard.NewCardActivity
import nextstep.payments.ui.theme.PaymentsTheme

class CreditCardsActivity : ComponentActivity() {
    private val viewModel: CreditCardsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val launcher =
                rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                    if (it.resultCode == RESULT_OK) {
                        viewModel.fetchCards()
                    }
                }
            PaymentsTheme {
                CreditCardsScreen(
                    viewModel = viewModel,
                    onAddClick = {
                        val intent = Intent(this, NewCardActivity::class.java)
                        launcher.launch(intent)
                    }
                )
            }
        }
    }
}