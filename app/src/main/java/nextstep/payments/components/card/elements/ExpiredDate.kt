package nextstep.payments.components.card.elements

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import nextstep.payments.components.card.DefaultPaymentCardScope
import nextstep.payments.components.card.PaymentCard
import nextstep.payments.domain.Card
import nextstep.payments.ui.theme.PaymentsTheme
import nextstep.payments.ui.theme.Typography

@Composable
fun PaymentCard.ExpiredDate(modifier: Modifier = Modifier) {
    Text(
        text = formatExpiredDate(card.expiredDate),
        color = Color.White,
        style = Typography.bodySmall,
        modifier = modifier,
    )
}

private fun formatExpiredDate(expiredDate: String): String {
    return expiredDate
        .chunked(2)
        .joinToString(separator = " / ")
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
