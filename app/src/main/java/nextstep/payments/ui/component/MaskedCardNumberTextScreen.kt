package nextstep.payments.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MaskedCardNumberText(
    cardNumber: String,
    cardColor: Color,
    modifier: Modifier = Modifier
) {
    val formattedNumber = formatCardNumber(cardNumber)
    val maskedNumber = maskCardNumber(formattedNumber)

    Row(modifier = modifier) {
        maskedNumber.split(" ").forEachIndexed { index, part ->
            if (index > 0) {
                Text(
                    text = "-",
                    style = MaterialTheme.typography.bodySmall,
                    color = if (cardColor.luminance() > 0.5) Color.Black else Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(1f)

                )
            }
            Text(
                text = part,
                style = MaterialTheme.typography.bodySmall,
                color = if (cardColor.luminance() > 0.5) Color.Black else Color.White
            )
        }
    }
}

fun formatCardNumber(number: String): String {
    val cleaned = number.replace("\\D".toRegex(), "") // 숫자만 남김
    return cleaned.take(16).chunked(4).joinToString(" ") // 4자리씩 묶어서 공백으로 구분
}

fun maskCardNumber(formattedNumber: String): String {
    val parts = formattedNumber.split(" ")
    return parts.mapIndexed { index, part ->
        when (index) {
            0, 1 -> part // 앞 8자리는 그대로 표시
            else -> "*".repeat(part.length) // 나머지는 * 로 마스킹
        }
    }.joinToString(" ")
}

@Preview(showBackground = true)
@Composable
private fun MaskedCardNumberTextPreview() {
    MaskedCardNumberText("1234567890123456", Color.White)
}