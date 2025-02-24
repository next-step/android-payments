package nextstep.payments.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.payments.R
import nextstep.payments.designsystem.theme.PaymentCardTextStyle
import nextstep.payments.designsystem.theme.PaymentsTheme
import nextstep.payments.designsystem.transformed.cardNumberTransformedText
import nextstep.payments.designsystem.transformed.expiredDateTransformedText
import nextstep.payments.ext.setContentDescription
import nextstep.payments.model.BankType
import nextstep.payments.model.Card
import java.time.YearMonth

@Composable
fun PaymentCard(
    type: BankType,
    modifier: Modifier = Modifier,
) {
    PaymentCard(modifier = modifier
        .shadow(8.dp)
        .background(
            color = type.backgroundColor,
            shape = RoundedCornerShape(5.dp)
        ),
        content = {
            Text(
                text = stringResource(type.bankName),
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 14.dp, top = 14.dp),
                fontSize = 14.sp,
                color = Color.White
            )
        }
    )
}

@Composable
fun PaymentCard(
    item: Card,
    onItemClick: (Card) -> Unit,
    modifier: Modifier = Modifier,
) {
    PaymentCard(
        modifier = modifier
            .shadow(8.dp)
            .background(
                color = item.type.backgroundColor,
                shape = RoundedCornerShape(5.dp)
            )
            .clickable(onClick = {
                onItemClick(item)
            }),
        content = {
            Text(
                text = stringResource(item.type.bankName),
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 14.dp, top = 14.dp),
                fontSize = 14.sp,
                color = Color.White
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 14.dp, end = 14.dp, bottom = 16.dp)
                    .align(Alignment.BottomCenter),
                verticalArrangement = Arrangement.spacedBy(2.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = cardNumberTransformedText(
                            text = AnnotatedString(item.number),
                            isMasked = true
                        ).text,
                        modifier = Modifier
                            .fillMaxWidth()
                            .setContentDescription("payment_card_number"),
                        style = PaymentCardTextStyle,
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(stringResource(R.string.crew), style = PaymentCardTextStyle)
                    Text(
                        expiredDateTransformedText(AnnotatedString(item.getStringExpiredDate())).text,
                        style = PaymentCardTextStyle,
                        modifier = Modifier.setContentDescription("payment_card_expired_date")
                    )
                }
            }
        }
    )
}


@Composable
fun PaymentCard(
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit = {}
) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = modifier
            .size(width = 208.dp, height = 124.dp)
            .setContentDescription("payment_card")
    ) {
        Box(
            modifier = Modifier
                .setContentDescription("card_chip")
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

@Preview(showBackground = true)
@Composable
private fun PaymentCardContainerPreview1() {
    PaymentsTheme {
        PaymentCard(
            modifier = Modifier
                .shadow(8.dp)
                .background(
                    color = Color(0xFF333333),
                    shape = RoundedCornerShape(5.dp)
                )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PaymentCardPreview2() {
    PaymentsTheme {
        PaymentCard(type = BankType.HANA)
    }
}

@Preview(showBackground = true)
@Composable
private fun PaymentCardPreview3() {
    val card = Card(
        type = BankType.BC,
        number = "1111222233334444",
        expiredDate = YearMonth.of(21, 4),
        ownerName = "",
        password = ""
    )
    PaymentsTheme {
        PaymentCard(
            item = card,
            onItemClick = {

            }
        )
    }
}