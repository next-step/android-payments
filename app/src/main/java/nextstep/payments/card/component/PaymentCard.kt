package nextstep.payments.card.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PaymentCard(
    cardColor: Color,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(
                width = 208.dp,
                height = 124.dp,
            )
            .background(
                color = cardColor,
                shape = RoundedCornerShape(5.dp),
            )
    ) {
        IcChip(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 14.dp, bottom = 10.dp)
        )
    }
}

@Composable
private fun IcChip(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(
                width = 40.dp,
                height = 26.dp,
            )
            .background(
                color = Color(0xFFCBBA64),
                shape = RoundedCornerShape(4.dp),
            )
    )
}

@Preview(showBackground = true)
@Composable
private fun PaymentCardPreview() {
    PaymentCard(
        cardColor = Color(0xFF333333)
    )
}
