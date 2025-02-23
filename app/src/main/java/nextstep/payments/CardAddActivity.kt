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
                val card by viewModel.card.collectAsStateWithLifecycle()
                val cardAdded by viewModel.cardAdded.collectAsStateWithLifecycle()
                val cardCompanyBottomSheet by viewModel.cardCompanyBottomSheet.collectAsStateWithLifecycle()

                CardAddScreen(
                    card = card,
                    cardAdded = cardAdded,
                    onCardNumberChange = viewModel::setCardNumber,
                    onExpiredDateChange = viewModel::setExpiredDate,
                    onOwnerNameChange = viewModel::setOwnerName,
                    onPasswordChange = viewModel::setPassword,
                    onBackPressed = { onBackPressedDispatcher.onBackPressed() },
                    onAddClicked = viewModel::addCard,
                    bottomSheetState = cardCompanyBottomSheet,
                    onCardCompanyChange = viewModel::setCardCompany,
                    onCardCompanyBottomSheetState = viewModel::setCardCompanyBottomSheetState,
                )
            }
        }
    }
}
