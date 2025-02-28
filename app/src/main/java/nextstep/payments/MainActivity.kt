package nextstep.payments

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import nextstep.payments.screens.card.list.CardListScreen
import nextstep.payments.screens.card.list.CardListViewModel
import nextstep.payments.screens.card.new.NewCardActivity
import nextstep.payments.ui.theme.PaymentsTheme

class MainActivity : ComponentActivity() {
    private val cardListViewModel: CardListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PaymentsTheme {
                val launcher =
                    rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                        if (it.resultCode == RESULT_OK) {
                            cardListViewModel.fetchCards()
                        }
                    }

                CardListScreen(
                    onAddCardClick = {
                        val intent: Intent = NewCardActivity.getIntent(this)
                        launcher.launch(intent)
                    },
                    viewModel = cardListViewModel,
                )
            }
        }
    }
}
