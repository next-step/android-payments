package nextstep.payments.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PaymentCard(
    modifier: Modifier = Modifier,
    cardColor: Color = Color(0xFF333333)
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = cardColor,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        modifier = modifier.size(width = 208.dp, height = 124.dp)
    ) {
        ChipBox(Modifier.padding(top = 44.dp, start = 14.dp))
    }
}

@Composable
fun ChipBox(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(width = 40.dp, height = 26.dp)
            .background(
                color = Color(0xFFCBBA64),
                shape = RoundedCornerShape(4.dp)
            )
    )
}

@Preview(showBackground = true)
@Composable
fun ChipBoxPreview() {
    ChipBox()
}

@Preview(showBackground = true)
@Composable
fun PaymentCardPreview() {
    PaymentCard()
}
