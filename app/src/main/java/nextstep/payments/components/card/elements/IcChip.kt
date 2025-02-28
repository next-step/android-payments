package nextstep.payments.components.card.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.ui.theme.PaymentsTheme
import nextstep.payments.ui.theme.Yellow100

@Composable
fun IcChip(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(width = 40.dp, height = 26.dp)
            .background(
                color = Yellow100,
                shape = RoundedCornerShape(4.dp),
            )
    )
}

@Preview
@Composable
private fun IcChipPreview() {
    PaymentsTheme {
        IcChip()
    }
}
