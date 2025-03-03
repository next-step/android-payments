package nextstep.payments.components.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import nextstep.payments.screens.card.CardCompanyState
import nextstep.payments.components.card.elements.CardCompanyName
import nextstep.payments.components.card.elements.CardNumbers
import nextstep.payments.components.card.elements.ExpiredDate
import nextstep.payments.components.card.elements.IcChip
import nextstep.payments.components.card.elements.OwnerName
import nextstep.payments.domain.Card
import nextstep.payments.domain.CardCompany
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun RegisteredPaymentCard(
    card: Card,
    modifier: Modifier = Modifier,
) {
    val cardCompanyState =
        remember(card) { CardCompanyState.findCardCompanyById(card.cardCompany.id) }

    BaseCard(
        modifier = modifier,
        color = cardCompanyState.backgroundColor,
    ) {
        CardCompanyName(
            name = stringResource(cardCompanyState.nameRes)
        )
        Spacer(Modifier.height(14.dp))
        IcChip()
        Spacer(Modifier.height(8.dp))
        CardNumbers(cardNumbers = card.numbers)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OwnerName(name = card.ownerName)
            ExpiredDate(date = card.expiredDate)
        }
    }
}

class RegisteredPaymentCardPreviewParameterProvider : PreviewParameterProvider<Int> {
    override val values: Sequence<Int> =
        CardCompanyState
            .entries
            .map(CardCompanyState::id)
            .asSequence()
}

@Preview
@Composable
private fun RegisteredPaymentCardPreview(
    @PreviewParameter(RegisteredPaymentCardPreviewParameterProvider::class) cardCompanyId: Int,
) {
    PaymentsTheme {
        val card = Card(
            numbers = "1111222200000000",
            expiredDate = "0421",
            ownerName = "CREW",
            password = "0000",
            cardCompany = CardCompany(cardCompanyId, "BC"),
        )
        RegisteredPaymentCard(
            card = card,
        )
    }
}
