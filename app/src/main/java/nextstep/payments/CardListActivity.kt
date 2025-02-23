package nextstep.payments

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.payments.data.repository.PaymentCardsRepository
import nextstep.payments.ui.list.CardListScreen
import nextstep.payments.ui.list.CardListViewModel
import nextstep.payments.ui.theme.PaymentsTheme

class CardListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PaymentsTheme {
                val cardListViewModel: CardListViewModel = viewModel(
                    factory = CardListViewModel.getFactory(PaymentCardsRepository)
                )

                CardListScreen(
                    cardListViewModel = cardListViewModel,
                    onAddCardClick = {
                        CardAddActivity.open(this)
                    }
                )
            }
        }
    }
}
