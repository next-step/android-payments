package nextstep.payments.ui.card.list.component.card

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun CardExpiredDate(expiredDate: String, modifier: Modifier = Modifier) {
    Text(
        text = expiredDate,
        modifier = modifier,
        style = MaterialTheme.typography.bodyMedium,
        color = Color.White
    )
}

@Preview
@Composable
private fun CardExpiredDatePreview() {
    PaymentsTheme {
        CardExpiredDate("12/24")
    }
}
