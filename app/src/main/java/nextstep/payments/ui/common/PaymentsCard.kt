package nextstep.payments.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
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
import nextstep.payments.ui.theme.PaymentsTheme
import nextstep.payments.utils.maskCardNumber

@Composable
fun PaymentCard(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit = {}
) {
    Box(
        modifier
            .shadow(8.dp)
            .size(width = 208.dp, height = 124.dp)
            .background(
                color = Color(0xFF333333),
                shape = RoundedCornerShape(5.dp),
            ),
        Alignment.CenterStart
    ) {
        Box(
            modifier = Modifier
                .padding(
                    start = 14.dp,
                    bottom = 10.dp
                )
                .size(
                    width = 40.dp,
                    height = 26.dp
                )
                .background(
                    color = Color(0xFFCBBA64),
                    shape = RoundedCornerShape(4.dp),
                )
        )
        content()
    }
}

@Composable
fun PaymentCard(
    modifier: Modifier = Modifier,
    card: Card
) {
    PaymentCard(modifier) {
        ProvideTextStyle(
            value = MaterialTheme.typography.bodySmall.copy(
                color = Color.White,
                fontWeight = FontWeight.Medium
            )
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(14.dp),
            ) {
                Text(
                    text = card.cardNumber.maskCardNumber(),
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = card.ownerName,
                    )
                    Text(
                        text = card.expiredDate,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PaymentCardWithInfoEmptyPreview() {
    PaymentsTheme {
        PaymentCard()
    }
}

@Preview
@Composable
private fun PaymentCardWithInfoPreview() {
    PaymentsTheme {
        PaymentCard(
            card = Card(
                cardNumber = "0000-0000-0000-0000",
                expiredDate = "00 / 00",
                ownerName = "컴포즈",
                password = "0000",
            ),
        )
    }
}
