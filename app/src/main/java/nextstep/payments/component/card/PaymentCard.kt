package nextstep.payments.component.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun PaymentCard(
    modifier: Modifier = Modifier,
    cardNumber : String = "",
    ownerName : String = "",
    expiredDate : String = "",
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
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 14.dp, bottom = 16.dp, end = 14.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            CardNumberText(cardNumber = cardNumber)
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                OwnerNameText(
                    modifier = Modifier
                        .wrapContentHeight()
                        .weight(1f),
                    ownerName = ownerName
                )
                ExpiredDateText(
                    expiredDate = expiredDate
                )
            }
        }
    }
}

@Composable
fun CardNumberText(
    cardNumber : String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = cardNumber,
        style = MaterialTheme.typography.labelMedium,
        color = Color.White
    )
}

@Composable
fun OwnerNameText(
    ownerName: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = ownerName,
        style = MaterialTheme.typography.labelMedium,
        color = Color.White
    )
}

@Composable
fun ExpiredDateText(
    expiredDate: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = expiredDate,
        style = MaterialTheme.typography.labelMedium,
        color = Color.White
    )
}

@Preview(showBackground = true, name = "CardNumberText", backgroundColor  =0xFF333333 )
@Composable
private fun Preview1() {
    PaymentsTheme {
        CardNumberText(
            cardNumber = "1111 - 2222 - **** - ****"
        )
    }
}

@Preview(showBackground = true, name = "OwnerNameText", backgroundColor  =0xFF333333 )
@Composable
private fun Preview2() {
    PaymentsTheme {
        OwnerNameText(
            ownerName = "CREW"
        )
    }
}

@Preview(showBackground = true, name = "ExpiredDateText", backgroundColor  = 0xFF333333 )
@Composable
private fun Preview3() {
    PaymentsTheme {
        ExpiredDateText(
            expiredDate = "04 / 21"
        )
    }
}

@Preview(showBackground = true, name = "PaymentCard", backgroundColor  = 0xFF333333 )
@Composable
private fun Preview4() {
    PaymentsTheme {
        PaymentCard(
            cardNumber = "1111 - 2222 - **** - ****",
            ownerName = "CREW",
            expiredDate = "04 / 21"
        )
    }
}

