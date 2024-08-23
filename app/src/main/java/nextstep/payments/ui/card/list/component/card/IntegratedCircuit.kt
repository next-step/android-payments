package nextstep.payments.ui.card.list.component.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun IntegratedCircuit(modifier: Modifier) {
    Spacer(
        modifier = modifier
            .background(
                Color(0xFFCBBA64),
                shape = MaterialTheme.shapes.small
            )
    )
}


@Preview
@Composable
private fun IntegratedCircuitPreview() {
    PaymentsTheme {
        IntegratedCircuit(
            modifier = Modifier
                .padding(top = 44.dp, start = 14.dp)
                .size(width = 40.dp, height = 26.dp)
        )
    }
}
