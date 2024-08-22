package nextstep.payments.ui.component.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import nextstep.payments.ui.utils.toTransformedCardNumber

@Composable
fun CardNumberText(
    modifier: Modifier = Modifier,
    cardNumber: String,
) {
    Text(
        modifier = modifier,
        text = cardNumber.toTransformedCardNumber(
            maxLength = 16,
            maskStartIndex = 12,
            maskChar = '*',
            groupSize = 4,
            separator = "-"
        ),
    )
}

@Preview(showBackground = true)
@Composable
private fun CardNumberTextPreview() {
    CardNumberText(cardNumber = "1111222233334444")
}
