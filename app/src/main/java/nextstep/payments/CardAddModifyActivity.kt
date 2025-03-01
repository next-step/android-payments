package nextstep.payments

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.payments.data.model.Card
import nextstep.payments.ui.CardAddModifyScreen
import nextstep.payments.ui.theme.PaymentsTheme
import nextstep.payments.utils.PARAM_CARD_ID
import nextstep.payments.viewmodel.CardAddViewModel

class CardAddModifyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val initialCardId = intent.getStringExtra(PARAM_CARD_ID).orEmpty()

        setContent {
            PaymentsTheme {
                val viewModel: CardAddViewModel = viewModel()
                val initialCard = viewModel.initialCard(initialCardId)
                val card by viewModel.card.collectAsStateWithLifecycle(initialCard)
                val cardAdded by viewModel.cardAdded.collectAsStateWithLifecycle()
                val cardCompanyBottomSheet by viewModel.cardCompanyBottomSheet.collectAsStateWithLifecycle()

                CardAddModifyScreen(
                    isModify = initialCard != Card.Empty,
                    card = card,
                    actionClicked = cardAdded,
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
