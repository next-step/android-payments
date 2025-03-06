package nextstep.payments.screens.card.update

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.R
import nextstep.payments.components.card.EmptyPaymentCard
import nextstep.payments.components.card.NewPaymentCard
import nextstep.payments.domain.Card
import nextstep.payments.domain.CardCompany
import nextstep.payments.screens.card.CardCompanyState
import nextstep.payments.screens.card.update.components.CardCompanyBottomSheetDialog
import nextstep.payments.screens.card.update.components.CardInformationInputFields
import nextstep.payments.screens.card.update.components.UpdateCardTopBar
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun EditCardScreen(
    uiState: UpdateCardUiState.EditCardUiState,
    onCardCompanyClick: (CardCompanyState) -> Unit,
    onCardNumberChange: (String) -> Unit,
    onExpiredDateChange: (String) -> Unit,
    onOwnerNameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var showCardCompanyBottomSheet by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            UpdateCardTopBar(
                title = stringResource(R.string.edit_card_top_bar_title),
                doneButtonEnabled = uiState.isFormValid(),
                onBackClick = onBackClick,
                onSaveClick = onSaveClick,
            )
        },
        modifier = modifier,
        containerColor = Color.White,
    ) { innerPadding ->
        if (showCardCompanyBottomSheet) {
            CardCompanyBottomSheetDialog(
                onDismissRequest = { showCardCompanyBottomSheet = false },
                onCardCompanyClick = onCardCompanyClick,
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(14.dp))

            if (uiState.cardState.selectedCardCompany == null) {
                EmptyPaymentCard()
            } else {
                NewPaymentCard(uiState.cardState.selectedCardCompany)
            }

            Spacer(modifier = Modifier.height(40.dp))

            CardInformationInputFields(
                cardNumber = uiState.cardState.cardNumber,
                expiredDate = uiState.cardState.expiredDate,
                ownerName = uiState.cardState.ownerName,
                password = uiState.cardState.password,
                onCardNumberChange = onCardNumberChange,
                onExpiredDateChange = onExpiredDateChange,
                onOwnerNameChange = onOwnerNameChange,
                onPasswordChange = onPasswordChange,
            )
        }
    }
}

@Preview
@Composable
private fun EditCardScreenPreview() {
    PaymentsTheme {
        EditCardScreen(
            uiState = UpdateCardUiState.EditCardUiState(
                cardForEdit = Card(
                    numbers = "",
                    expiredDate = "",
                    ownerName = "",
                    password = "",
                    cardCompany = CardCompany.HANA
                ),
                cardState = CardState(
                    selectedCardCompany = null,
                    cardNumber = "",
                    expiredDate = "",
                    ownerName = "",
                    password = ""
                ),
                cardUpdated = false,
            ),
            onCardCompanyClick = {},
            onCardNumberChange = {},
            onExpiredDateChange = {},
            onOwnerNameChange = {},
            onPasswordChange = {},
            onBackClick = {},
            onSaveClick = {},
        )
    }
}
