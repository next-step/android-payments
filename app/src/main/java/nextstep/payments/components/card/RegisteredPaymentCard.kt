package nextstep.payments.components.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import androidx.compose.ui.unit.dp
import nextstep.payments.components.card.elements.CardCompanyName
import nextstep.payments.components.card.elements.CardNumbers
import nextstep.payments.components.card.elements.ExpiredDate
import nextstep.payments.components.card.elements.IcChip
import nextstep.payments.components.card.elements.OwnerName
import nextstep.payments.screens.card.uistate.CardCompanyUiState
import nextstep.payments.screens.card.uistate.CardUiState
import nextstep.payments.ui.theme.Black100
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun RegisteredPaymentCard(
    cardUiState: CardUiState,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    BaseCard(
        onClick = onClick,
        modifier = modifier,
        color = cardUiState.selectedCardCompany?.backgroundColor ?: Black100,
    ) {
        CardCompanyName(
            name = if (cardUiState.selectedCardCompany == null) {
                ""
            } else {
                stringResource(cardUiState.selectedCardCompany.nameRes)
            }
        )
        Spacer(Modifier.height(14.dp))
        IcChip()
        Spacer(Modifier.height(8.dp))
        CardNumbers(cardNumbers = cardUiState.cardNumber)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OwnerName(name = cardUiState.ownerName)
            ExpiredDate(date = cardUiState.expiredDate)
        }
    }
}

class RegisteredPaymentCardPreviewParameterProvider :
    CollectionPreviewParameterProvider<CardCompanyUiState>(collection = CardCompanyUiState.entries)

@Preview
@Composable
private fun RegisteredPaymentCardPreview(
    @PreviewParameter(RegisteredPaymentCardPreviewParameterProvider::class) cardCompany: CardCompanyUiState,
) {
    PaymentsTheme {
        val cardUiState = CardUiState(
            id = 0,
            cardNumber = "1111222200000000",
            expiredDate = "0421",
            ownerName = "CREW",
            password = "0000",
            selectedCardCompany = cardCompany,
        )
        RegisteredPaymentCard(
            cardUiState = cardUiState,
            onClick = {},
        )
    }
}
