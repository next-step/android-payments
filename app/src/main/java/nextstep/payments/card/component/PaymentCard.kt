package nextstep.payments.card.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.payments.card.Card

@Composable
fun PaymentCard(
    cardColor: Color,
    modifier: Modifier = Modifier,
    card: Card? = null,
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

        if (card != null) {
            CardInfo(
                card = card,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = 14.dp)
                    .padding(bottom = 16.dp),
            )
        }
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

@Composable
private fun CardInfo(
    card: Card,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        Text(
            text = card.cardNumber,
            color = Color.White,
            fontSize = 12.sp,
            letterSpacing = 1.sp,
        )

        Spacer(modifier = Modifier.height(2.dp))

        Row {
            Text(
                text = card.ownerName,
                color = Color.White,
                fontSize = 12.sp,
                letterSpacing = 0.5.sp,
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = card.expireDate,
                color = Color.White,
                fontSize = 12.sp,
                letterSpacing = 0.5.sp,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PaymentCardPreview() {
    PaymentCard(
        cardColor = Color(0xFF333333)
    )
}

@Preview(showBackground = true)
@Composable
private fun PaymentCardPreview_withCard() {
    PaymentCard(
        cardColor = Color(0xFF333333),
        card = Card(
            cardNumber = "1111 - 2222 - 3333 - 4444",
            expireDate = "04/21",
            ownerName = "Crew",
            cvcNumber = "000",
            password = "1234",
        )
    )
}
