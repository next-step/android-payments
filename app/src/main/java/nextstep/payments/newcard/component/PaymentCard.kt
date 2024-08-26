package nextstep.payments.newcard.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import nextstep.payments.model.CreditCard
import nextstep.payments.ui.theme.PaymentsTheme

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
    ) {
        Column(
            modifier = Modifier.padding(start = 14.dp, bottom = 10.dp)
        ) {
            // 카드의 칩 모양
            Box(
                modifier = Modifier
                    .size(width = 40.dp, height = 26.dp)
                    .background(
                        color = Color(0xFFCBBA64),
                        shape = RoundedCornerShape(4.dp),
                    )
            )

            // 카드 번호와 사용자 정보
            Column(
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(
                    text = creditCard?.cardNumber ?: "**** - **** - **** - ****",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

                Row(
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Text(
                        text = creditCard?.ownerName ?: "CARD HOLDER",
                        color = Color.White,
                        fontSize = 14.sp,
                        modifier = Modifier.weight(1f)
                    )

                    Text(
                        text = creditCard?.expiredDate ?: "MM / YY",
                        color = Color.White,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PaymentCardPreview() {
    PaymentsTheme {
        PaymentCard(
            creditCard = CreditCard(
                cardNumber = "1111222233334444",
                expiredDate = "0421",
                password = "1234",
                ownerName = "CREW"
            )
        )
    }
}