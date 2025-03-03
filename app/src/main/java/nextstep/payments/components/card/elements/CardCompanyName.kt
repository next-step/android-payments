package nextstep.payments.components.card.elements

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun CardCompanyName(
    name: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = name,
        modifier = modifier,
        style = MaterialTheme.typography.bodySmall.copy(letterSpacing = 2.0.sp),
        color = Color.White,
    )
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
private fun CardCompanyNamePreview() {
    PaymentsTheme {
        CardCompanyName(name = "BC카드")
    }
}
