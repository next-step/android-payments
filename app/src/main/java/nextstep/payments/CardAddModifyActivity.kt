package nextstep.payments

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.payments.data.model.Card
import nextstep.payments.ui.CardAddModifyScreen
import nextstep.payments.ui.theme.PaymentsTheme
import nextstep.payments.utils.PARAM_CARD_ID
import nextstep.payments.utils.ResultCode
import nextstep.payments.utils.toCardList
import nextstep.payments.viewmodel.CardAddViewModel

class CardAddModifyActivity : ComponentActivity() {
    private val toast = Toast.makeText(this, "", Toast.LENGTH_SHORT)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val initialCardId = intent.getStringExtra(PARAM_CARD_ID).orEmpty()

        setContent {
            PaymentsTheme {
                val viewModel: CardAddViewModel = viewModel<CardAddViewModel>()

                val initialCard by remember { mutableStateOf(viewModel.initializeCard(cardId = initialCardId)) }

                val card by viewModel.card.collectAsStateWithLifecycle()
                val cardCompanyBottomSheet by viewModel.cardCompanyBottomSheet.collectAsStateWithLifecycle()
                val navigateToCardList by viewModel.navigateToCardList.collectAsStateWithLifecycle()
                val showToast by viewModel.showToast.collectAsStateWithLifecycle()

                CardAddModifyScreen(
                    isModify = initialCard != Card.Empty,
                    card = card,
                    onCardNumberChange = viewModel::setCardNumber,
                    onExpiredDateChange = viewModel::setExpiredDate,
                    onOwnerNameChange = viewModel::setOwnerName,
                    onPasswordChange = viewModel::setPassword,
                    onBackPressed = { onBackPressedDispatcher.onBackPressed() },
                    onAddClicked = viewModel::addCard,
                    onModifyClicked = viewModel::modifyCard,
                    bottomSheetState = cardCompanyBottomSheet,
                    onCardCompanyChange = viewModel::setCardCompany,
                    onCardCompanyBottomSheetState = viewModel::setCardCompanyBottomSheetState,
                )

                if (navigateToCardList) toCardList()

                showToast(showToast)
            }
        }
    }

    private fun showToast(showToast: ResultCode) {
        showToast.takeIf { it != ResultCode.IDLE }?.let { resultCode ->
            toast.apply {
                setText(resultCode.stringResId)
                show()
            }
        }
    }
}
