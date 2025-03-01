package nextstep.payments.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.payments.R
import nextstep.payments.data.model.Card
import nextstep.payments.data.model.CardCompany
import nextstep.payments.ui.component.Card
import nextstep.payments.ui.component.CardAddTopBar
import nextstep.payments.ui.component.CardInputFields
import nextstep.payments.ui.component.CardSelectSheet
import nextstep.payments.ui.theme.PaymentsTheme
import nextstep.payments.utils.toCardList
import nextstep.payments.viewmodel.CardAddViewModel
import nextstep.payments.viewmodel.CardCompanyBottomSheetState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardAddModifyScreen(
    card: Card,
    cardAdded: Long,
    bottomSheetState: CardCompanyBottomSheetState,
    onCardCompanyChange: (CardCompany) -> Unit,
    onCardCompanyBottomSheetState: (CardCompanyBottomSheetState) -> Unit,
    onCardNumberChange: (String) -> Unit,
    onExpiredDateChange: (String) -> Unit,
    onOwnerNameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onBackPressed: () -> Unit,
    onAddClicked: () -> Unit,
) {

    val context = LocalContext.current

    LaunchedEffect(cardAdded) {
        if (card.company != null) {
            context.showToast(context.getString(R.string.success_add_card))
            context.toCardList()
        } else {
            context.showToast(context.getString(R.string.request_select_card_company))
            onCardCompanyBottomSheetState(CardCompanyBottomSheetState.Show)
        }
    }

    val modalBottomSheetState = rememberModalBottomSheetState(
        confirmValueChange = { false }
    )

    Scaffold(
        topBar = {
            CardAddTopBar(
                onBackClick = onBackPressed,
                onCheckClick = onAddClicked,
            )
        },
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter,
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(18.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp, vertical = 14.dp)
            ) {

                Card(model = card)
                CardInputFields(
                    card = card,
                    onCardNumberChange = onCardNumberChange,
                    onExpiredDateChange = onExpiredDateChange,
                    onOwnerNameChange = onOwnerNameChange,
                    onPasswordChange = onPasswordChange,
                )
            }

            if (bottomSheetState == CardCompanyBottomSheetState.Show) {
                ModalBottomSheet(
                    sheetState = modalBottomSheetState,
                    onDismissRequest = { onCardCompanyBottomSheetState(CardCompanyBottomSheetState.Hide) },
                ) {
                    CardSelectSheet(
                        selectedCompany = card.company,
                        onCardCompanyChange = onCardCompanyChange
                    )
                }
            }
        }
    }
}

private fun cardNumberFormattedText(text: String) {
    text.replace("-", "")
        .format("")
}

private val Context.toast get() = Toast.makeText(this, "", Toast.LENGTH_SHORT)
private fun Context.showToast(message: String) {
    toast.apply {
        setText(message)
        show()
    }
}

@Composable
fun CardAddModifyScreen(
    onBackPressed: () -> Unit,
    viewModel: CardAddViewModel = viewModel(),
) {

    val card by viewModel.card.collectAsStateWithLifecycle()
    val cardAdded by viewModel.cardAdded.collectAsStateWithLifecycle()
    val cardCompanyBottomSheet by viewModel.cardCompanyBottomSheet.collectAsStateWithLifecycle()

    CardAddModifyScreen(
        card = card,
        cardAdded = cardAdded,
        onCardNumberChange = viewModel::setCardNumber,
        onExpiredDateChange = viewModel::setCardNumber,
        onOwnerNameChange = viewModel::setCardNumber,
        onPasswordChange = viewModel::setCardNumber,
        onBackPressed = { onBackPressed.invoke() },
        onAddClicked = viewModel::addCard,
        bottomSheetState = cardCompanyBottomSheet,
        onCardCompanyChange = viewModel::setCardCompany,
        onCardCompanyBottomSheetState = viewModel::setCardCompanyBottomSheetState,
    )
}

@Preview
@Composable
private fun StatelessNewCardScreenPreview() {
    PaymentsTheme {
        CardAddModifyScreen(onBackPressed = {})
    }
}