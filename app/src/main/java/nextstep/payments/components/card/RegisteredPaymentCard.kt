package nextstep.payments.components.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.components.card.elements.ExpiredDate
import nextstep.payments.components.card.elements.IcChip
import nextstep.payments.components.card.elements.Numbers
import nextstep.payments.components.card.elements.OwnerName
import nextstep.payments.domain.Card
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun RegisteredPaymentCard(
    card: Card,
    modifier: Modifier = Modifier,
) {
    BaseCard(
        card = card,
        modifier = modifier,
    ) {
        Column {
            Spacer(Modifier.weight(1f))
            IcChip()
            Spacer(Modifier.height(8.dp))
            Numbers()
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OwnerName()
                ExpiredDate()
            }
        }
    }
}

@Preview
@Composable
private fun RegisteredPaymentCardPreview() {
    PaymentsTheme {
        val card = Card(
            numbers = "1111222200000000",
            expiredDate = "0421",
            ownerName = "CREW",
            password = "0000"
        )
        RegisteredPaymentCard(
            card = card,
        )
    }
}
