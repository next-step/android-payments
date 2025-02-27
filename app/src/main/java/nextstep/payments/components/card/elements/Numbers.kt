package nextstep.payments.components.card.elements

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import nextstep.payments.components.card.DefaultPaymentCardScope
import nextstep.payments.components.card.PaymentCard
import nextstep.payments.domain.Card
import nextstep.payments.ui.theme.PaymentsTheme
import nextstep.payments.ui.theme.Typography

private const val MASK_SYMBOL = "*"
private const val CARD_NUMBERS_SEPARATOR = " - "

@Composable
fun PaymentCard.Numbers(modifier: Modifier = Modifier) {
    Text(
        text = formatCardNumbers(card.numbers),
        color = Color.White,
        style = Typography.bodySmall.copy(letterSpacing = 2.0.sp),
        modifier = modifier,
    )
}

private fun formatCardNumbers(numbers: String): String {
    val maskedCardNumbers = numbers.take(8) + MASK_SYMBOL.repeat(8)
    return maskedCardNumbers
        .chunked(4)
        .joinToString(separator = CARD_NUMBERS_SEPARATOR)
}

@Preview
@Composable
private fun NumbersPreview() {
    PaymentsTheme {
        val card = Card(
            numbers = "1111222200000000",
            expiredDate = "0000",
            ownerName = "Moon SangHyun",
            password = "0000"
        )
        val paymentCard = DefaultPaymentCardScope(card)
        paymentCard.Numbers()
    }
}
