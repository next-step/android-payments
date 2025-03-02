package nextstep.payments.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.payments.model.CreditCard

@Composable
fun PaymentCard(
    modifier: Modifier = Modifier,
    creditCard: CreditCard? = null,
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
            .padding(14.dp)
    ) {
        Box(
            modifier = Modifier
                .size(width = 40.dp, height = 26.dp)
                .background(
                    color = Color(0xFFCBBA64),
                    shape = RoundedCornerShape(4.dp),
                )
                .align(Alignment.CenterStart)
        )

        if (creditCard != null) {
            PaymentCardText(
                text = creditCard.maskedNumber,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(top = 64.dp)
            )

            PaymentCardText(
                text = creditCard.ownerName,
                modifier = Modifier.align(Alignment.BottomStart)
            )

            PaymentCardText(
                text = creditCard.expiredDate,
                modifier = Modifier.align(Alignment.BottomEnd)
            )
        }
    }
}

@Composable
private fun PaymentCardText(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        color = Color.White,
        fontSize = 12.sp,
        modifier = modifier,
        style = TextStyle(letterSpacing = 2.sp)
    )
}

@Preview
@Composable
private fun PaymentCardPreview() {
    PaymentCard(
        creditCard = CreditCard.mock,
    )
}