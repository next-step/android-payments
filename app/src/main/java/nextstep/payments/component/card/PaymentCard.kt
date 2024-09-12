package nextstep.payments.component.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.payments.R
import nextstep.payments.screen.model.BankTypeUiModel
import nextstep.payments.screen.model.CreditCardUiModel
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun PaymentCardFrame(
    bankType: BankTypeUiModel?,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    content: @Composable BoxScope.() -> Unit = {}
) {
    CardFrame(
        modifier = modifier,
        backgroundColor = bankType?.color ?: Color(0xFF333333),
        onClick = onClick
    ) {
        bankType?.let { bankType ->
            BankTypeRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopStart)
                    .padding(top = 15.dp, start = 14.dp, end = 14.dp),
                bankType = bankType
            )
        }
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

@Composable
fun PaymentCard(
    bankType: BankTypeUiModel?,
    onClick : () -> Unit,
    modifier: Modifier = Modifier,
) {
    PaymentCardFrame(
        bankType = bankType,
        modifier = modifier,
        onClick = onClick
    )
}

@Composable
fun PaymentCard(
    card: CreditCardUiModel,
    onClick: (CreditCardUiModel) -> Unit,
    modifier: Modifier = Modifier
) {
    PaymentCardFrame(
        modifier = modifier,
        bankType = card.bankTypeUiModel,
        onClick = {
            onClick(card)
        },
        content = {
            Column(
                modifier = modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 14.dp, bottom = 16.dp, end = 14.dp),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                CardNumberText(
                    cardNumber = stringResource(
                        id = R.string.card_number,
                        card.firstCardDigits,
                        card.secondCardDigits
                    )
                )
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OwnerNameText(
                        modifier = Modifier
                            .wrapContentHeight()
                            .weight(1f),
                        ownerName = card.ownerName,
                    )
                    ExpiredDateText(
                        expiredDate = stringResource(
                            id = R.string.expired_date,
                            card.month,
                            card.year
                        )
                    )
                }
            }
        }
    )
}

@Composable
fun CardNumberText(
    cardNumber: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier.testTag("cardNumberText"),
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
        modifier = modifier.testTag("expiredDateText"),
        text = expiredDate,
        style = MaterialTheme.typography.labelMedium,
        color = Color.White
    )
}

@Composable
fun BankTypeRow(
    bankType: BankTypeUiModel,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(id = bankType.nameStringRes),
            fontSize = 12.sp,
            color = Color.White
        )
        Image(
            modifier = Modifier.clip(CircleShape).background(Color.White),
            painter = painterResource(id = bankType.imageDrawableRes),
            contentDescription = bankType.name
        )
    }

}

@Preview(showBackground = true, name = "CardNumberText", backgroundColor = 0xFF333333)
@Composable
private fun Preview1() {
    PaymentsTheme {
        CardNumberText(
            cardNumber = "1111 - 2222 - **** - ****"
        )
    }
}

@Preview(showBackground = true, name = "OwnerNameText", backgroundColor = 0xFF333333)
@Composable
private fun Preview2() {
    PaymentsTheme {
        OwnerNameText(
            ownerName = "CREW"
        )
    }
}

@Preview(showBackground = true, name = "ExpiredDateText", backgroundColor = 0xFF333333)
@Composable
private fun Preview3() {
    PaymentsTheme {
        ExpiredDateText(
            expiredDate = "04 / 21"
        )
    }
}

@Preview(showBackground = true, name = "PaymentCard", backgroundColor = 0xFF333333)
@Composable
private fun Preview4() {
    PaymentsTheme {
        PaymentCard(
            card = CreditCardUiModel(
                cardNumber = "1234123412341234",
                firstCardDigits = "1234",
                secondCardDigits = "1234",
                ownerName = "김컴포즈",
                password = "1234",
                month = "12",
                year = "12",
                bankTypeUiModel = BankTypeUiModel.BC
            ),
            onClick = {}
        )
    }
}

@Preview(showBackground = true, name = "PaymentCardWithoutBankType", backgroundColor = 0xFF333333)
@Composable
private fun Preview5() {
    PaymentsTheme {
        PaymentCard(
            bankType = null,
            onClick = {},
        )
    }
}

