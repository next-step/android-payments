package nextstep.payments.components.card

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.components.card.elements.IcChip
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun EmptyPaymentCard(
    modifier: Modifier = Modifier,
) {
    BaseCard(modifier = modifier) {
        Spacer(Modifier.weight(1f))
        IcChip()
        Spacer(Modifier.height(10.dp))
        Spacer(Modifier.weight(1f))
    }
}

@Preview(showBackground = true)
@Composable
private fun EmptyPaymentCardPreview() {
    PaymentsTheme {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(40.dp),
        ) {
            EmptyPaymentCard()
        }
    }
}
