package nextstep.payments

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.payments.ui.CardAddScreen
import nextstep.payments.ui.theme.PaymentsTheme
import nextstep.payments.viewmodel.CardAddViewModel

class CardAddActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PaymentsTheme {
                val viewModel: CardAddViewModel = viewModel()
                val cardModel by viewModel.card.collectAsStateWithLifecycle()
                val cardAdded by viewModel.cardAdded.collectAsStateWithLifecycle()

                CardAddScreen(
                    card = cardModel,
                    cardAdded = cardAdded,
                    onCardNumberChange = viewModel::setCardNumber,
                    onExpiredDateChange = viewModel::setCardNumber,
                    onOwnerNameChange = viewModel::setCardNumber,
                    onPasswordChange = viewModel::setCardNumber,
                    onBackPressed = { onBackPressedDispatcher.onBackPressed() },
                    onAddClicked = viewModel::addCard,
                )
            }
        }
    }
}
