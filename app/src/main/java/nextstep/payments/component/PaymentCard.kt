package nextstep.payments.component

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
import androidx.compose.ui.unit.em
import nextstep.payments.model.CardNumber
import nextstep.payments.model.CreditCard
import nextstep.payments.ui.theme.PaymentsTheme
import java.time.YearMonth
import java.time.format.DateTimeFormatter

@Composable
internal fun PaymentCard() {
    CardFrame()
}

@Composable
internal fun PaymentCard(
    card: CreditCard,
) {
    CardFrame {
        CardDetails(card)
    }
}

@Composable
fun CardFrame(
    content: @Composable () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .shadow(8.dp)
            .size(width = 208.dp, height = 124.dp)
            .background(
                color = Color(0xFF333333),
                shape = RoundedCornerShape(5.dp),
            )
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 14.dp)
                .padding(top = 44.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(width = 40.dp, height = 26.dp)
                    .background(
                        color = Color(0xFFCBBA64),
                        shape = RoundedCornerShape(4.dp),
                    )
            )
            content() // Default content is empty if not provided
        }
    }
}

@Composable
fun CardDetails(card: CreditCard) {
    Spacer(modifier = Modifier.height(8.dp))
    CardNumber(
        numberFirst = card.cardNumbers[0],
        numberSecond = card.cardNumbers[1],
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(2.dp))
    CardOwnerExpiredDate(
        ownerName = card.ownerName,
        expiredDate = card.expiredDate,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun CardNumber(
    numberFirst: CardNumber,
    numberSecond: CardNumber,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = "${numberFirst.number} - ${numberSecond.number} - **** - ****",
        style = PaymentsTheme.typography.roboto12M.copy(letterSpacing = 0.17.em),
        color = Color.White,
    )
}

@Composable
private fun CardOwnerExpiredDate(
    ownerName: String,
    expiredDate: YearMonth,
    modifier: Modifier = Modifier
) {
    val formatter = DateTimeFormatter.ofPattern("MM / yy")
    val formattedDate = expiredDate.format(formatter)

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = ownerName,
            style = PaymentsTheme.typography.roboto12M.copy(letterSpacing = 0.1.em),
            color = Color.White
        )

        Text(
            text = formattedDate,
            style = PaymentsTheme.typography.roboto12M.copy(letterSpacing = 0.1.em),
            color = Color.White
        )

    }
}

@Preview
@Composable
private fun PaymentCardPreview() {
    PaymentsTheme {
        PaymentCard(
            card = CreditCard(
                cardNumbers = listOf(
                    CardNumber("1111"),
                    CardNumber("2222"),
                    CardNumber("3333"),
                    CardNumber("4444")
                ),
                expiredDate = YearMonth.now(),
                password = "1234",
                ownerName = "CREW"
            )
        )
    }
}

@Preview
@Composable
private fun NoCreditCardPaymentCardPreview() {
    PaymentsTheme {
        PaymentCard()
    }
}