package nextstep.payments.newcard.component

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.payments.model.CreditCard
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun PaymentCard(
    modifier: Modifier = Modifier,
    creditCard: CreditCard? = null
) {
    Column(
        verticalArrangement = Arrangement.Center,
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
        creditCard?.let { cardInfo ->
            CreditCardInfo(creditCard = cardInfo, Modifier.padding(horizontal = 14.dp))
        }
    }
}

@Composable
fun CreditCardInfo(creditCard: CreditCard, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        CardNumber(creditCard.number)
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CardOwnerName(creditCard.ownerName)
            CardExpireDate(creditCard.expiredDate)
        }
    }
}

@Composable
private fun CardExpireDate(expiredDate: String) {
    Text(
        text = "${expiredDate.substring(0, 2)} / ${expiredDate.substring(2)}",
        color = Color.White,
        fontSize = 12.sp,
    )
}

@Composable
private fun CardOwnerName(ownerName: String) {
    Text(
        text = ownerName,
        color = Color.White,
        fontSize = 12.sp,
    )
}

@Composable
private fun CardNumber(cardNumber: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = cardNumber.substring(0, 4),
            color = Color.White,
            fontSize = 12.sp
        )
        Text(
            text = "-",
            color = Color.White,
            fontSize = 12.sp
        )
        Text(
            text = cardNumber.substring(4, 8),
            color = Color.White,
            fontSize = 12.sp
        )
        Text(
            text = "-",
            color = Color.White,
            fontSize = 12.sp
        )
        Text(
            text = "****",
            color = Color.White,
            fontSize = 12.sp
        )
        Text(
            text = "-",
            color = Color.White,
            fontSize = 12.sp
        )
        Text(
            text = "****",
            color = Color.White,
            fontSize = 12.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PaymentCardPreview() {
    PaymentsTheme {
        PaymentCard(creditCard = CreditCard("1234567812345678", "0421", "김무현", "1234"))
    }
}

@Preview
@Composable
private fun CreditCardInfoPreview() {
    PaymentsTheme {
        CreditCardInfo(creditCard = CreditCard("1234567812345678", "0421", "김무현", "1234"))
    }
}
