package nextstep.payments.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.model.BankType
import nextstep.payments.model.Card
import nextstep.payments.newcard.model.BankTypeUiModel
import nextstep.payments.ui.theme.PaymentsTheme
import nextstep.payments.ui.theme.TypoTokens.Medium12

@Composable
fun ClickablePaymentCard(
    card: Card,
    modifier: Modifier = Modifier,
    onClickCardItem: (Card) -> Unit = {},
) {
    PaymentCard(
        cardNumber = card.cardNumber,
        expiredDate = card.expiredDate,
        ownerName = card.ownerName,
        bankType = BankTypeUiModel.from(card.bankType),
        modifier = modifier.clickable { onClickCardItem(card) },
    )
}

@Composable
fun PaymentCard(
    cardNumber: String,
    expiredDate: String,
    ownerName: String,
    bankType: BankTypeUiModel,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .shadow(8.dp)
            .size(width = 208.dp, height = 124.dp)
            .background(color = bankType.color)
            .clip(shape = RoundedCornerShape(5.dp))
    ) {
        Text(
            text = bankType.title,
            style = Medium12,
            color = Color.White,
            modifier = Modifier
                .padding(start = 14.dp, top = 10.dp)
                .align(Alignment.TopStart)
        )
        Box(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 14.dp, bottom = 10.dp)
                .size(width = 40.dp, height = 26.dp)
                .background(
                    color = Color(0xFFCBBA64),
                    shape = RoundedCornerShape(4.dp),
                )
        )
        PaymentCardInfo(
            cardNumber = cardNumber,
            expiredDate = expiredDate,
            ownerName = ownerName,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp)
                .padding(bottom = 16.dp)
                .align(Alignment.BottomStart)
        )
    }
}

@Composable
fun PaymentCardInfo(
    cardNumber: String,
    expiredDate: String,
    ownerName: String,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = cardNumber.toCardNumberFormat(),
            style = Medium12,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(2.dp))
        Row(
            horizontalArrangement = Arrangement.Absolute.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = ownerName,
                style = Medium12,
                color = Color.White
            )
            Text(
                text = expiredDate,
                style = Medium12,
                color = Color.White
            )
        }
    }
}

val cardNumberPattern = Regex("""^\d{4}-\d{4}-\d{4}-\d{4}$""")
private fun String.toCardNumberFormat(): String {
    if (cardNumberPattern.matches(this).not()) {
        return ""
    }
    return this.split("-")
        .mapIndexed { index, number ->
            if (index > 1) {
                "****"
            } else {
                number
            }
        }
        .joinToString(" - ")
}

@Preview
@Composable
private fun PaymentCardEmptyPreview() {
    PaymentsTheme {
        PaymentCard(
            cardNumber = "",
            ownerName = "",
            expiredDate = "",
            bankType = BankTypeUiModel.from(BankType.NOT_SELECTED),
        )
    }
}

@Preview
@Composable
private fun PaymentCardPreview() {
    PaymentsTheme {
        PaymentCard(
            cardNumber = "1111-2222-3333-4444",
            ownerName = "CREW",
            expiredDate = "04 / 31",
            bankType = BankTypeUiModel.from(BankType.KB),
        )
    }
}
