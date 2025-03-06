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
import nextstep.payments.screens.card.state.CardCompanyState
import nextstep.payments.screens.card.state.CardState
import nextstep.payments.ui.theme.Black100
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun RegisteredPaymentCard(
    card: CardState,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    BaseCard(
        onClick = onClick,
        modifier = modifier,
        color = card.selectedCardCompany?.backgroundColor ?: Black100,
    ) {
        CardCompanyName(
            name = if (card.selectedCardCompany == null) {
                ""
            } else {
                stringResource(card.selectedCardCompany.nameRes)
            }
        )
        Spacer(Modifier.height(14.dp))
        IcChip()
        Spacer(Modifier.height(8.dp))
        CardNumbers(cardNumbers = card.cardNumber)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OwnerName(name = card.ownerName)
            ExpiredDate(date = card.expiredDate)
        }
    }
}

class RegisteredPaymentCardPreviewParameterProvider :
    CollectionPreviewParameterProvider<CardCompanyState>(collection = CardCompanyState.entries)

@Preview
@Composable
private fun RegisteredPaymentCardPreview(
    @PreviewParameter(RegisteredPaymentCardPreviewParameterProvider::class) cardCompany: CardCompanyState,
) {
    PaymentsTheme {
        val card = CardState(
            cardNumber = "1111222200000000",
            expiredDate = "0421",
            ownerName = "CREW",
            password = "0000",
            selectedCardCompany = cardCompany,
        )
        RegisteredPaymentCard(
            card = card,
            onClick = {},
        )
    }
}
