package nextstep.payments.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.payments.model.Card

@Composable
fun PaymentCard(
    modifier: Modifier = Modifier,
    card: Card? = null,
) {
    Box(
        modifier = modifier
            .shadow(8.dp)
            .size(width = 208.dp, height = 124.dp)
            .background(
                color = Color(0xFF333333),
                shape = RoundedCornerShape(5.dp),
            )
            .padding(14.dp),
    ) {
        Box(
            modifier = Modifier
                .padding(bottom = 10.dp)
                .size(width = 40.dp, height = 26.dp)
                .background(
                    color = Color(0xFFCBBA64),
                    shape = RoundedCornerShape(4.dp),
                )
                .align(Alignment.CenterStart),
        )

        card?.let {
            Column(modifier = Modifier.align(Alignment.BottomStart)) {
                Text(
                    text = it.cardNumber,
                    color = Color.White,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    lineHeight = 14.sp,
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = it.ownerName,
                        color = Color.White,
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp,
                        lineHeight = 14.sp,
                    )
                    Text(
                        text = it.expiredDate,
                        color = Color.White,
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp,
                        lineHeight = 14.sp,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PaymentCardPreview() {
    val card = Card(
        cardNumber = "0000 - 0000 - 0000 - 0000",
        expiredDate = "08/27",
        ownerName = "jay kang",
        password = "1234",
    )
    Column(verticalArrangement = Arrangement.spacedBy(32.dp)) {
        PaymentCard()
        PaymentCard(card = card)
    }
}
