package nextstep.payments.components.card.elements

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import nextstep.payments.ui.theme.PaymentsTheme

private const val MASK_SYMBOL = "*"
private const val CARD_NUMBERS_SEPARATOR = " - "
private const val CARD_NUMBERS_GROUP_SIZE = 4
private const val CARD_NUMBERS_NON_MASKED_LENGTH = 8
private const val CARD_NUMBERS_MASKED_LENGTH = 8

@Composable
fun CardNumbers(
    cardNumbers: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = formatCardNumbers(cardNumbers),
        color = Color.White,
        style = MaterialTheme.typography.bodySmall.copy(letterSpacing = 2.0.sp),
        modifier = modifier,
    )
}

private fun formatCardNumbers(numbers: String): String {
    val maskedCardNumbers =
        numbers.take(CARD_NUMBERS_NON_MASKED_LENGTH) + MASK_SYMBOL.repeat(CARD_NUMBERS_MASKED_LENGTH)

    return maskedCardNumbers
        .chunked(CARD_NUMBERS_GROUP_SIZE)
        .joinToString(separator = CARD_NUMBERS_SEPARATOR)
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
private fun CardNumbersPreview() {
    PaymentsTheme {
        CardNumbers(cardNumbers = "1111222200000000")
    }
}
