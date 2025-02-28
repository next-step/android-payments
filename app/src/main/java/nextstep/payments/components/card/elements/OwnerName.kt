package nextstep.payments.components.card.elements

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun OwnerName(
    name: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = name,
        color = Color.White,
        style = MaterialTheme.typography.bodySmall,
        modifier = modifier,
    )
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
private fun OwnerNamePreview() {
    PaymentsTheme {
        OwnerName(name = "MOON")
    }
}
