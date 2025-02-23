package nextstep.payments.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement.Absolute.SpaceBetween
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
import androidx.compose.ui.text.font.FontWeight.Companion.W500
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.payments.model.CreditCard
import nextstep.payments.ui.theme.PaymentsTheme


@Composable
fun PaymentCard(
    paymentCard: CreditCard,
    modifier: Modifier = Modifier,
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
    ) {
        Box(
            modifier = Modifier
                .padding(start = 14.dp, bottom = 10.dp)
                .size(width = 40.dp, height = 26.dp)
                .background(
                    color = Color(0xFFCBBA64),
                    shape = RoundedCornerShape(4.dp),
                )
        )
        PaymentCardContent(
            paymentCard = paymentCard,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 16.dp),
        )
    }
}

@Composable
fun PaymentCardContent(
    paymentCard: CreditCard,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = maskCardNumber(paymentCard.cardNumber),
            color = Color.White,
            fontSize = 12.sp,
            fontWeight = W500,
            letterSpacing = 2.sp,
            lineHeight = 12.sp,
            modifier = Modifier.fillMaxWidth(),
        )
        Row(
            horizontalArrangement = SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = paymentCard.ownerName,
                color = Color.White,
                fontSize = 12.sp,
                lineHeight = 12.sp,
                fontWeight = W500,
            )
            Text(
                text = maskExpiredDate(paymentCard.expiredDate),
                color = Color.White,
                fontSize = 12.sp,
                lineHeight = 12.sp,
                fontWeight = W500,
            )
        }
    }
}

private fun maskCardNumber(cardNumber: String): String {
    if (cardNumber.length !in 14..16) return cardNumber
    return "${cardNumber.substring(0, 4)} - ${cardNumber.substring(4, 8)} - **** - ****"
}

private fun maskExpiredDate(expiredDate: String): String {
    if (expiredDate.length != 4) return expiredDate
    return "${expiredDate.substring(0, 2)} / ${expiredDate.substring(2, 4)}"
}

class PaymentsTopBarPreviewParameterProvider : PreviewParameterProvider<CreditCard> {
    override val values = sequenceOf(
        CreditCard(
            cardNumber = "1234567890123456",
            expiredDate = "1231",
            ownerName = "홍길동",
            password = "123"
        ),
        CreditCard(
            cardNumber = "",
            expiredDate = "",
            ownerName = "",
            password = ""
        )
    )
}

@Preview
@Composable
private fun PaymentCardPreview(
    @PreviewParameter(PaymentsTopBarPreviewParameterProvider::class) paymentCard: CreditCard
) {
    PaymentsTheme {
        PaymentCard(
            paymentCard = paymentCard
        )
    }
}