package nextstep.payments.ui.paymentcards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun PaymentCard(
    cardNumber: String,
    ownerName: String,
    expiredDate: String,
    modifier: Modifier = Modifier,
) {
    Surface(
        shape = RoundedCornerShape(5.dp),
        shadowElevation = 8.dp,
        color = Color(0xFF333333),
        contentColor = Color.White,
        modifier = modifier.size(width = 208.dp, height = 124.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 14.dp, vertical = 8.dp),
        ) {
            Box(
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .size(width = 40.dp, height = 26.dp)
                    .background(
                        color = Color(0xFFCBBA64),
                        shape = RoundedCornerShape(4.dp),
                    )
            )

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = cardNumber,
                color = Color.White,
                letterSpacing = 0.25.em,
                fontSize = 12.sp,
                maxLines = 1,
                fontWeight = FontWeight.Bold
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(ownerName, color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                Text(
                    expiredDate,
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview
@Composable
private fun PaymentCardPreview() {
    PaymentsTheme {
        PaymentCard(
            cardNumber = "1234-1234-1234-1234",
            expiredDate = "12/23",
            ownerName = "홍길동",
        )
    }
}