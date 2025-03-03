package nextstep.payments.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
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
import nextstep.payments.data.Card
import nextstep.payments.data.dummyDataList

@Composable
fun PaymentCard(
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
    }
}

@Composable
fun PaymentListCard(
    card: Card,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .shadow(8.dp)
            .size(width = 208.dp, height = 124.dp)
            .background(
                color = Color(0xFF333333),
                shape = RoundedCornerShape(5.dp),
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 14.dp)
        ) {
            Spacer(modifier = Modifier.size(44.dp))
            Box(
                modifier = Modifier
                    .size(width = 40.dp, height = 26.dp)
                    .background(
                        color = Color(0xFFCBBA64),
                        shape = RoundedCornerShape(4.dp),
                    )
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = card.formatCardNumber(),
                modifier = Modifier.fillMaxWidth(),
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.W500,
                letterSpacing = (12.sp * 0.17),
                lineHeight = 14.06.sp,
            )
            Spacer(modifier = Modifier.size(2.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = card.ownerName,
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W500,
                    letterSpacing = (12.sp * 0.1),
                    lineHeight = 14.06.sp,
                )
                Text(
                    text = card.formatExpiredDate(),
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W500,
                    letterSpacing = (12.sp * 0.08),
                    lineHeight = 14.06.sp,
                )
            }
        }
    }
}

@Composable
fun EnrollmentPaymentCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .shadow(8.dp)
            .size(width = 208.dp, height = 124.dp)
            .background(
                color = Color(0xFFE5E5E5),
                shape = RoundedCornerShape(5.dp),
            )
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "+",
            color = Color(0xFF575757),
            fontSize = 34.sp,
            fontWeight = FontWeight.W400,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PaymentCardTypePreview() {
    PaymentCard()
}

@Preview(showBackground = true)
@Composable
private fun PaymentCardListTypePreview() {
    val card = dummyDataList.first()
    PaymentListCard(card = card)
}

@Preview(showBackground = true)
@Composable
private fun EnrollmentPaymentCardTypePreview() {
    EnrollmentPaymentCard()
}