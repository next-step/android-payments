package nextstep.payments.ui.component.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import nextstep.payments.model.card.CardNumber
import nextstep.payments.ui.component.graphics.getDominantColorFromDrawable
import nextstep.payments.ui.theme.PaymentsTheme
import java.time.YearMonth
import java.time.format.DateTimeFormatter

@Composable
internal fun PaymentCard() {
    CardFrame()
}

@Composable
internal fun PaymentCard(
    cardBankInformation: CardBankInformation
) {
    val color = Color(
        getDominantColorFromDrawable(
            context = LocalContext.current,
            drawableRes = cardBankInformation.iconRes
        )
    )
    CardFrame(
        color = color,
        titleContent = {
            Text(
                text = stringResource(id = cardBankInformation.nameRes),
                style = PaymentsTheme.typography.roboto12M.copy(letterSpacing = 0.1.em),
                color = Color.White
            )
        }
    )
}

@Composable
internal fun PaymentCard(
    cardInformation: CardInformation
) {
    val color = Color(
        getDominantColorFromDrawable(
            context = LocalContext.current,
            drawableRes = cardInformation.bank.iconRes
        )
    )
    CardFrame(
        color = color,
        titleContent = {
            Text(
                text = stringResource(id = cardInformation.bank.nameRes),
                style = PaymentsTheme.typography.roboto12M.copy(letterSpacing = 0.1.em),
                color = Color.White
            )
        }
    ) {
        CardDetails(
            cardInformation,
        )
    }
}

@Composable
fun CardFrame(
    color: Color = Color(0xFF333333),
    titleContent: @Composable RowScope.() -> Unit = {},
    content: @Composable () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .shadow(8.dp)
            .size(width = 208.dp, height = 124.dp)
            .background(
                color = color,
                shape = RoundedCornerShape(5.dp),
            )
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 14.dp)
                .padding(top = 15.dp)
        ) {
            Row(modifier = Modifier.height(14.dp)) {
                titleContent()
            }

            Spacer(modifier = Modifier.height(15.dp))

            Box(
                modifier = Modifier
                    .size(width = 40.dp, height = 26.dp)
                    .background(
                        color = Color(0xFFCBBA64),
                        shape = RoundedCornerShape(4.dp),
                    ),
            )
            Spacer(modifier = Modifier.height(8.dp))

            content() // Default content is empty if not provided
        }
    }
}

@Composable
internal fun CardDetails(
    cardInformation: CardInformation,
) {
    Column {
        CardNumber(
            numberFirst = cardInformation.numberFirst,
            numberSecond = cardInformation.numberSecond,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(2.dp))
        CardOwnerExpiredDate(
            ownerName = cardInformation.ownerName,
            expirationDate = cardInformation.expirationDate,
            modifier = Modifier.fillMaxWidth()
        )
    }
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
    expirationDate: YearMonth,
    modifier: Modifier = Modifier
) {
    val formatter = DateTimeFormatter.ofPattern("MM / yy")
    val formattedDate = expirationDate.format(formatter)

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
            cardInformation = CardInformation(
                numberFirst = CardNumber("1111"),
                numberSecond = CardNumber("2222"),
                expirationDate = YearMonth.now(),
                ownerName = "CREW",
                bank = CardBankInformation.Bc
            )
        )
    }
}

@Preview
@Composable
private fun CardBankInformationPaymentCardPreview() {
    PaymentsTheme {
        PaymentCard(
            cardBankInformation = CardBankInformation.Bc
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