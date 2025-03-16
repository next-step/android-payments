package nextstep.payments.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.payments.data.BankType
import nextstep.payments.data.Card

@Composable
fun PaymentCard(
    card: Card,
    modifier: Modifier = Modifier,
) {
    CardBackground(
        backgroundColor = card.bankType?.cardColor ?: Color(0xFF333333),
        modifier = modifier,
    ) {
        Spacer(modifier = Modifier.height(15.dp))

        CardBankName(
            bankName = card.bankType?.krName ?: "",
            modifier = Modifier.padding(horizontal = 14.dp)
        )

        Spacer(modifier = Modifier.height(15.dp))

        CardIcChipImage(modifier = Modifier.padding(horizontal = 14.dp))

        Spacer(modifier = Modifier.height(8.dp))

        CardInfo(
            card = card,
            modifier = Modifier.padding(horizontal = 14.dp),
        )
    }
}

@Composable
fun EnrollmentPaymentCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    CardBackground(
        backgroundColor = Color(0xFFE5E5E5),
        modifier = modifier.clickable {
            onClick()
        },
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            Text(
                text = "+",
                color = Color(0xFF575757),
                fontSize = 34.sp,
                fontWeight = FontWeight.W400,
                modifier = Modifier.align(Alignment.Center),
            )
        }
    }
}

@Composable
private fun CardBackground(
    backgroundColor: Color,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    Card(
        modifier = modifier
            .shadow(8.dp)
            .size(208.dp, 124.dp),
        shape = RoundedCornerShape(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor,
        ),
    ) {
        content()
    }
}

@Composable
private fun CardBankName(
    bankName: String,
    modifier: Modifier = Modifier
) {
    CardText(
        text = bankName,
        letterSpacing = (12.sp * 0.1),
        modifier = modifier.testTag("PaymentCard_BankName")
    )
}

@Composable
private fun CardIcChipImage(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(width = 40.dp, height = 26.dp)
            .background(
                color = Color(0xFFCBBA64),
                shape = RoundedCornerShape(4.dp),
            ),
    )
}

@Composable
private fun CardInfo(
    card: Card,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        CardNumber(
            cardNumber = card.formatCardNumber(),
        )
        CardNameAndExpiredDate(
            ownerName = card.ownerName,
            expiredDate = card.formatExpiredDate(),
        )
    }
}

@Composable
private fun CardNumber(
    cardNumber: String,
    modifier: Modifier = Modifier
) {
    CardText(
        text = cardNumber,
        letterSpacing = (12.sp * 0.17),
        modifier = modifier.testTag("PaymentCard_CardNumber"),
    )
}

@Composable
private fun CardNameAndExpiredDate(
    ownerName: String,
    expiredDate: String,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        CardText(
            text = ownerName,
            letterSpacing = (12.sp * 0.1),
            modifier = Modifier.testTag("PaymentCard_OwnerName"),
        )
        CardText(
            text = expiredDate,
            letterSpacing = (12.sp * 0.08),
            modifier = Modifier.testTag("PaymentCard_ExpiredDate"),
        )
    }
}

@Composable
private fun CardText(
    text: String,
    letterSpacing: TextUnit,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        modifier = modifier,
        color = Color.White,
        fontSize = 12.sp,
        fontWeight = FontWeight.W500,
        letterSpacing = letterSpacing,
        lineHeight = 14.06.sp,
    )
}

@Preview(showBackground = true)
@Composable
private fun PaymentCardTypePreview() {
    val card = Card(
        cardNumber = "",
        expiredDate = "",
        ownerName = "",
        password = "",
        bankType = null
    )

    PaymentCard(card = card)
}

@Preview(showBackground = true)
@Composable
private fun PaymentCardListTypePreview() {
    val card = Card(
        cardNumber = "1234567890123456",
        expiredDate = "1201",
        ownerName = "홍길동",
        password = "0000",
        bankType = BankType.KB
    )
    PaymentCard(
        card = card,
    )
}

@Preview(showBackground = true)
@Composable
private fun EnrollmentPaymentCardTypePreview() {
    EnrollmentPaymentCard()
}