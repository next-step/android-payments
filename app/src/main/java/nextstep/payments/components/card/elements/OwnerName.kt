package nextstep.payments.components.card.elements

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import nextstep.payments.components.card.DefaultPaymentCardScope
import nextstep.payments.components.card.PaymentCard
import nextstep.payments.domain.Card
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun PaymentCard.OwnerName(modifier: Modifier = Modifier) {
    Text(
        text = card.ownerName,
        color = Color.White,
        style = MaterialTheme.typography.bodySmall,
        modifier = modifier,
    )
}

@Preview
@Composable
private fun OwnerNamePreview() {
    PaymentsTheme {
        val card = Card(
            numbers = "0000000000000000",
            expiredDate = "0000",
            ownerName = "Moon SangHyun",
            password = "0000"
        )
        val paymentCard = DefaultPaymentCardScope(card)

        paymentCard.OwnerName()
    }
}
