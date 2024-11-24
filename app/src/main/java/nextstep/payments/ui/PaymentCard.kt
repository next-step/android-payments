package nextstep.payments.ui

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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun PaymentCard(
    modifier: Modifier = Modifier,
    cardCompany: String = "",
    content: @Composable () -> Unit = {},
) {
    val defaultModifier = Modifier
        .shadow(8.dp)
        .size(width = 208.dp, height = 124.dp)
        .background(
            color = Color(0xFF333333),
            shape = RoundedCornerShape(5.dp),
        )

    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = defaultModifier.then(modifier)
    ) {
        Column {
            Text(
                text = cardCompany,
                color = Color.White,
                fontSize = 12.sp,
                modifier = Modifier
                    .padding(start = 14.dp, bottom = 3.dp),
            )
            Box(
                modifier = Modifier
                    .padding(start = 14.dp, bottom = 10.dp)
                    .size(width = 40.dp, height = 26.dp)
                    .background(
                        color = Color(0xFFCBBA64),
                        shape = RoundedCornerShape(4.dp),
                    )
            )
            content()
        }
    }
}

@Preview
@Composable
fun BcCardPreview() {
    PaymentsTheme {
        PaymentCard(
            modifier = Modifier
                .background(
                    color = Color(0xFFF04651),
                    shape = RoundedCornerShape(5.dp),
                ),
            cardCompany = "BC카드",
            content = {
                Column(
                    modifier = Modifier
                        .padding(start = 14.dp, end = 14.dp)
                        .fillMaxWidth(),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "1234-0000-1111-1234",
                            color = Color.White,
                            fontSize = 12.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            textAlign = TextAlign.Start,
                            style = TextStyle(letterSpacing = 3.sp)
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,

                        ) {
                        Text(
                            text = "홍길동",
                            color = Color.White,
                            fontSize = 12.sp
                        )
                        Text(
                            text = "12/34",
                            color = Color.White,
                            fontSize = 12.sp
                        )
                    }
                }
            })
    }
}

@Preview
@Composable
fun EmptyPaymentCardPreview() {
    PaymentsTheme {
        PaymentCard()
    }
}
