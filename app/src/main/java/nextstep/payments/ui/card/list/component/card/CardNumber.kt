package nextstep.payments.ui.card.list.component.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun CardNumber(cardNumber: String, modifier: Modifier = Modifier) {
    val numberList = cardNumber.split("-")

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = numberList[0],
            color = Color.White,
            style = MaterialTheme.typography.bodyMedium,
        )

        Text(
            text = "-",
            color = Color.White,
            style = MaterialTheme.typography.bodyMedium,
        )

        Text(
            text = numberList[1],
            color = Color.White,
            style = MaterialTheme.typography.bodyMedium,
        )

        Text(
            text = "-",
            color = Color.White,
            style = MaterialTheme.typography.bodyMedium,
        )

        Text(
            text = numberList[2],
            color = Color.White,
            style = MaterialTheme.typography.bodyMedium,
        )

        Text(
            text = "-",
            color = Color.White,
            style = MaterialTheme.typography.bodyMedium,
        )

        Text(
            text = numberList[3],
            color = Color.White,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Preview(showBackground = true,
    backgroundColor = 0xFF333333)
@Composable
private fun CardNumberPreview() {
    PaymentsTheme {
        CardNumber(cardNumber = "9999-1234-5678-0000")
    }
}
