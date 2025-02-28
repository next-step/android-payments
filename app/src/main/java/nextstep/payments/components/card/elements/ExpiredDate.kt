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

private const val EXPIRED_DATE_GROUP_SIZE = 2
private const val EXPIRED_DATE_SEPARATOR = " / "

@Composable
fun PaymentCard.ExpiredDate(modifier: Modifier = Modifier) {
    Text(
        text = formatExpiredDate(card.expiredDate),
        color = Color.White,
        style = MaterialTheme.typography.bodySmall,
        modifier = modifier,
    )
}

private fun formatExpiredDate(expiredDate: String): String {
    return expiredDate
        .chunked(EXPIRED_DATE_GROUP_SIZE)
        .joinToString(separator = EXPIRED_DATE_SEPARATOR)
}

@Preview
@Composable
private fun ExpiredDatePreview() {
    PaymentsTheme {
        val card = Card(
            numbers = "0000000000000000",
            expiredDate = "0421",
            ownerName = "Moon SangHyun",
            password = "0000"
        )
        val paymentCard = DefaultPaymentCardScope(card)
        paymentCard.ExpiredDate()
    }
}
