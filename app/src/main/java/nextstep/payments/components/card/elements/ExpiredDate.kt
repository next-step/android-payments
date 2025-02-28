package nextstep.payments.components.card.elements

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import nextstep.payments.ui.theme.PaymentsTheme

private const val EXPIRED_DATE_GROUP_SIZE = 2
private const val EXPIRED_DATE_SEPARATOR = " / "

@Composable
fun ExpiredDate(
    date: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = formatExpiredDate(date),
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

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
private fun ExpiredDatePreview() {
    PaymentsTheme {
        ExpiredDate(date = "0421")
    }
}
