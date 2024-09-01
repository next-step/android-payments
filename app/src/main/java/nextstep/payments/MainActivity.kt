package nextstep.payments

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import nextstep.payments.screen.cardlist.CardListScreen
import nextstep.payments.screen.cardlist.CardListViewModel
import nextstep.payments.screen.newcard.NewCardActivity
import nextstep.payments.ui.theme.PaymentsTheme

class MainActivity : ComponentActivity() {
    private val viewModel : CardListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == RESULT_OK) {
                    viewModel.fetchCardList()
                }
            }
            PaymentsTheme {
                CardListScreen(
                    viewModel = viewModel,
                    navigateToNewCard = {
                        launcher.launch(Intent(this, NewCardActivity::class.java))
                    }
                )
            }
        }
    }
}
