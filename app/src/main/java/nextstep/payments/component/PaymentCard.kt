package nextstep.payments.component

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.payments.R
import nextstep.payments.component.PaymentCardDefaults.CHUNKED_DUE_DATE_LENGTH
import nextstep.payments.component.PaymentCardDefaults.CHUNKED_NUMBER_LENGTH
import nextstep.payments.component.PaymentCardDefaults.CardSize
import nextstep.payments.component.PaymentCardDefaults.DUE_DATE_LENGTH
import nextstep.payments.component.PaymentCardDefaults.UNMASKED_NUMBER_LENGTH
import nextstep.payments.model.CardCompany
import nextstep.payments.model.CreditCard
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun PaymentCard(
    card: CreditCard,
    modifier: Modifier = Modifier,
) {
    val formattedNumber = remember(key1 = card.number) { card.number.formatCardNumber() }
    val formattedDueDate = remember(key1 = card.dueDate) { card.dueDate.formatCardDueDate() }
    val cardCompanyResource = remember(key1 = card.company) { card.company.toResource() }

    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = modifier
            .shadow(8.dp)
            .size(CardSize)
            .background(
                color = cardCompanyResource?.backgroundColor ?: Color(0xFF333333),
                shape = RoundedCornerShape(5.dp),
            )
            .testTag("PaymentCard")
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 14.dp, vertical = 10.dp)
                .align(Alignment.BottomStart),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            if (card.company != CardCompany.NONE) {
                PaymentCardText(
                    text = cardCompanyResource?.stringRes?.let { stringResource(id = it) } ?: ""
                )
            }

            Spacer(
                modifier = Modifier
                    .size(width = 40.dp, height = 26.dp)
                    .background(
                        color = Color(0xFFCBBA64),
                        shape = RoundedCornerShape(4.dp),
                    )
            )

            PaymentCardText(
                text = formattedNumber
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                PaymentCardText(
                    text = card.name,
                )
                PaymentCardText(
                    text = formattedDueDate
                )
            }
        }
    }
}

private fun String.formatCardNumber(): String {
    val builder = StringBuilder()
    builder.append(this.take(UNMASKED_NUMBER_LENGTH))
    if (this.length - UNMASKED_NUMBER_LENGTH > 0) {
        builder.append("*".repeat(this.length - UNMASKED_NUMBER_LENGTH))
    }

    return builder.toString().chunked(CHUNKED_NUMBER_LENGTH).joinToString(" - ")
}

private fun String.formatCardDueDate(): String {
    return this.take(DUE_DATE_LENGTH).chunked(CHUNKED_DUE_DATE_LENGTH).joinToString("/")
}

@Composable
private fun PaymentCardText(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = text,
        color = Color.White,
        fontSize = 12.sp,
        lineHeight = 14.sp,
        letterSpacing = 2.sp
    )
}

@Composable
fun EmptyPaymentCard(
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = modifier
            .shadow(8.dp)
            .size(CardSize)
            .background(
                color = Color(0xFFE5E5E5),
                shape = RoundedCornerShape(5.dp),
            )
            .testTag("EmptyPaymentCard")
    ) {
        Icon(
            modifier = Modifier
                .size(20.dp)
                .align(Alignment.Center),
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(R.string.add),
            tint = Color(0xFF575757)
        )
    }
}

@Preview
@Composable
private fun PaymentCardPreview() {
    PaymentsTheme {
        Column {
            PaymentCard(
                card = CreditCard(
                    number = "1111222233334444",
                    dueDate = "0125",
                    password = "1234",
                    name = "YANG",
                    company = CardCompany.NONE
                )
            )
            EmptyPaymentCard()
            PaymentCard(
                card = CreditCard(
                    number = "1111222233334444",
                    dueDate = "0125",
                    password = "1234",
                    name = "YANG",
                    company = CardCompany.BC
                )
            )
        }
    }
}