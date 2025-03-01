package nextstep.payments.common.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.payments.common.model.Card

@Composable
fun PaymentCard(
    modifier: Modifier = Modifier,
    card: Card? = null,
) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = modifier
            .shadow(8.dp)
            .size(width = 208.dp, height = 124.dp)
            .background(
                color = Color(0xFF333333),
                shape = RoundedCornerShape(5.dp),
            )
            .padding(horizontal = 14.dp, vertical = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .padding(bottom = 10.dp)
                .size(width = 40.dp, height = 26.dp)
                .background(
                    color = Color(0xFFCBBA64),
                    shape = RoundedCornerShape(4.dp),
                )
        )

        if (card != null) {
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = maskCardNumber(card.cardNumber),
                    fontSize = 12.sp,
                    lineHeight = 14.06.sp,
                    letterSpacing = 2.04.sp,
                    fontWeight = FontWeight.W500,
                    color = Color.White,
                )
                Spacer(modifier = Modifier.height(2.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = card.ownerName,
                        fontSize = 12.sp,
                        lineHeight = 14.06.sp,
                        letterSpacing = 1.2.sp,
                        fontWeight = FontWeight.W500,
                        color = Color.White,
                    )
                    Text(
                        text = card.expiredDate,
                        fontSize = 12.sp,
                        lineHeight = 14.06.sp,
                        letterSpacing = 0.96.sp,
                        fontWeight = FontWeight.W500,
                        color = Color.White,
                    )
                }
            }
        }
    }
}

private fun maskCardNumber(cardNumber: String): String {
    val parts = cardNumber.split(" - ")
    return "${parts[0]} - ${parts[1]} - **** - ****"
}

class CardPreviewParameterProvider : PreviewParameterProvider<Card?> {
    override val values = sequenceOf(
        null,
        Card(
            cardNumber = "1111 - 2222 - 3333 - 4444",
            expiredDate = "12/25",
            ownerName = "CREW",
            password = "1234",
        )
    )
}
@Preview
@Composable
private fun PaymentCardPreview(
    @PreviewParameter(CardPreviewParameterProvider::class) card: Card?
) {
    PaymentCard(
        card = card
    )
}

