package nextstep.payments.ui.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CardNumberText(
    modifier: Modifier = Modifier,
    cardNumber: String,
) {
    Text(
        modifier = modifier,
        text = cardNumber.maskCardNumber(),
    )
}

private fun String.maskCardNumber(): String {
    return "${this.take(4)} - ${this.substring(4, 8)} - **** - ****"
}

@Preview(showBackground = true)
@Composable
private fun CardNumberTextPreview() {
    CardNumberText(cardNumber = "1111222233334444")
}
