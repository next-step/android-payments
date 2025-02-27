package nextstep.payments.components.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.components.card.elements.IcChip
import nextstep.payments.ui.theme.Black100
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun EmptyPaymentCard(
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = modifier
            .shadow(8.dp)
            .size(width = 208.dp, height = 124.dp)
            .background(
                color = Black100,
                shape = RoundedCornerShape(5.dp),
            )
    ) {
        IcChip(
            modifier = Modifier.padding(start = 14.dp, bottom = 10.dp)
        )
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
