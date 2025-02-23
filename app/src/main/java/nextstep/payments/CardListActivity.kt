package nextstep.payments

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import nextstep.payments.data.repository.PaymentCardsRepository
import nextstep.payments.ui.list.CardListScreen
import nextstep.payments.ui.list.CardListViewModel
import nextstep.payments.ui.theme.PaymentsTheme

class CardListActivity : ComponentActivity() {
    private val cardListViewModel: CardListViewModel by viewModels {
        CardListViewModel.getFactory(PaymentCardsRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PaymentsTheme {
                val launcher =
                    rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                        if (it.resultCode == RESULT_OK) {
                            cardListViewModel.refresh()
                        }
                    }

                CardListScreen(
                    cardListViewModel = cardListViewModel,
                    onAddCardClick = {
                        launcher.launch(Intent(this, CardAddActivity::class.java))
                    }
                )
            }
        }
    }
}
