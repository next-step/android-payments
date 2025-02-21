package nextstep.payments

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.compose.currentStateAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.payments.ui.CardListScreen
import nextstep.payments.ui.theme.PaymentsTheme
import nextstep.payments.viewmodel.CardListViewModel

class CardListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PaymentsTheme {
                val viewModel: CardListViewModel = viewModel()

                val lifecycleOwner = LocalLifecycleOwner.current
                val currentLifecycleState by lifecycleOwner.lifecycle.currentStateAsState()

                if (currentLifecycleState == Lifecycle.State.RESUMED) viewModel.fetchCards()

                val cardsUiState by viewModel.cardsUiState.collectAsStateWithLifecycle()

                CardListScreen(cardsUiState)
            }
        }
    }
}