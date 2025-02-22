package nextstep.payments.ui.screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.payments.ui.utils.TextFormatUtil

@Composable
fun PaymentCard(
    cardNumber: String,
    expiredDate: String,
    ownerName: String,
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
        Column(
            modifier = Modifier.padding(horizontal = 14.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(width = 40.dp, height = 26.dp)
                    .background(
                        color = Color(0xFFCBBA64),
                        shape = RoundedCornerShape(4.dp),
                    )
            )
            Spacer(modifier = Modifier.size(8.dp))
            CardNumberRow(cardNumber = cardNumber)
            Spacer(modifier = Modifier.size(2.dp))
            OwnerNameAndExpireDateRow(
                ownerName = ownerName,
                expiredDate = expiredDate,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun CardNumberRow(
    cardNumber: String,
    modifier: Modifier = Modifier
) {
    if (cardNumber.length != 16) {
        return CardNumber(" ")
    }

    val fistNumsSector = cardNumber.substring(0, 4)
    val secondNumsSector = cardNumber.substring(5, 9)

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        CardNumber(fistNumsSector)
        Divider()
        CardNumber(secondNumsSector)
        Divider()
        CardNumber("****")
        Divider()
        CardNumber("****")
    }
}

@Composable
private fun OwnerNameAndExpireDateRow(
    ownerName: String,
    expiredDate: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = ownerName,
            fontSize = 12.sp,
            fontWeight = FontWeight.W500,
            color = Color.White
        )
        Text(
            text = TextFormatUtil.formatDate(expiredDate),
            fontSize = 12.sp,
            fontWeight = FontWeight.W500,
            color = Color.White
        )
    }
}

@Composable
private fun CardNumber(
    number: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = number,
        modifier = modifier,
        fontSize = 12.sp,
        fontWeight = FontWeight.W500,
        color = Color.White
    )
}

@Composable
private fun Divider(modifier: Modifier = Modifier) {
    Text(
        text = "-",
        modifier = modifier,
        fontSize = 12.sp,
        fontWeight = FontWeight.W500,
        color = Color.White
    )
}

@Preview(showBackground = true)
@Composable
private fun CardNumberRowPreview() {
    CardNumberRow("1234123412341234")
}

@Preview(showBackground = true)
@Composable
private fun OwnerNameAndExpireDateRowPreview() {
    OwnerNameAndExpireDateRow("홍길동", "1234")
}

@Preview
@Composable
private fun CardNumberPreview() {
    CardNumber("1234")
}

@Preview(showBackground = true)
@Composable
private fun DividerPreview() {
    Divider()
}

@Preview(showBackground = true)
@Composable
private fun PaymentCardPreview() {
    PaymentCard(
        cardNumber = "1234123412341324",
        expiredDate = "1234",
        ownerName = "홍길동",
    )
}
