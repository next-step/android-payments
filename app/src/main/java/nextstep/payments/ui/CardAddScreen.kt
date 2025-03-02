package nextstep.payments.ui

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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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
import nextstep.payments.viewmodel.CardAddViewModel
import nextstep.payments.viewmodel.CardCompanyBottomSheetState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardAddModifyScreen(
    isModify: Boolean,
    card: Card,
    bottomSheetState: CardCompanyBottomSheetState,
    onCardCompanyChange: (CardCompany) -> Unit,
    onCardCompanyBottomSheetState: (CardCompanyBottomSheetState) -> Unit,
    onCardNumberChange: (String) -> Unit,
    onExpiredDateChange: (String) -> Unit,
    onOwnerNameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onBackPressed: () -> Unit,
    onAddClicked: () -> Unit,
    onModifyClicked: () -> Unit,
) {

    val modalBottomSheetState = rememberModalBottomSheetState(
        confirmValueChange = { false }
    )

    val title = stringResource(if (isModify) R.string.modify_card else R.string.add_card)

    Scaffold(
        topBar = {
            CardAddTopBar(
                title = title,
                onBackClick = onBackPressed,
                onCheckClick = if (isModify) onModifyClicked else onAddClicked,
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

                Card(model = card, enabled = false)
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

@Composable
fun CardAddModifyScreen(
    isModify: Boolean,
    onBackPressed: () -> Unit,
    viewModel: CardAddViewModel = viewModel(),
) {

    val card by viewModel.card.collectAsStateWithLifecycle()
    val cardCompanyBottomSheet by viewModel.cardCompanyBottomSheet.collectAsStateWithLifecycle()

    CardAddModifyScreen(
        isModify = isModify,
        card = card,
        onCardNumberChange = viewModel::setCardNumber,
        onExpiredDateChange = viewModel::setCardNumber,
        onOwnerNameChange = viewModel::setCardNumber,
        onPasswordChange = viewModel::setCardNumber,
        onBackPressed = { onBackPressed.invoke() },
        onAddClicked = viewModel::addCard,
        onModifyClicked = viewModel::modifyCard,
        bottomSheetState = cardCompanyBottomSheet,
        onCardCompanyChange = viewModel::setCardCompany,
        onCardCompanyBottomSheetState = viewModel::setCardCompanyBottomSheetState,
    )
}

@Preview
@Composable
private fun CardAddPreview() {
    PaymentsTheme {
        CardAddModifyScreen(
            isModify = false,
            onBackPressed = {}
        )
    }
}

@Preview
@Composable
private fun CardModifyPreview() {
    val card = Card(
        id = "0",
        number = "11113",
        ownerName = "홍길동",
        expiredDate = "10/04",
        password = "1111",
        company = CardCompany.BC,
    )

    PaymentsTheme {
        CardAddModifyScreen(
            card = card,
            bottomSheetState = CardCompanyBottomSheetState.Hide,
            isModify = true,
            onCardCompanyChange = {},
            onCardCompanyBottomSheetState = {},
            onCardNumberChange = {},
            onExpiredDateChange = {},
            onOwnerNameChange = {},
            onPasswordChange = {},
            onBackPressed = {},
            onAddClicked = {},
            onModifyClicked = {},
        )
    }
}