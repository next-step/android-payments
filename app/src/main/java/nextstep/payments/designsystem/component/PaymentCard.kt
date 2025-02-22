package nextstep.payments.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import nextstep.payments.R
import nextstep.payments.designsystem.theme.PaymentCardTextStyle
import nextstep.payments.designsystem.theme.PaymentsTheme
import nextstep.payments.designsystem.transformed.cardNumberTransformedText
import nextstep.payments.designsystem.transformed.expiredDateTransformedText
import nextstep.payments.ext.setContentDescription
import nextstep.payments.model.Card

object PaymentCard {

    @Composable
    operator fun invoke(
        modifier: Modifier = Modifier
    ) {
        PaymentCardContainer(modifier = modifier)
    }

    @Composable
    operator fun invoke(
        item: Card,
        onItemClick: (Card) -> Unit,
        modifier: Modifier = Modifier,
    ) {
        Box(
            modifier = modifier
                .setContentDescription("payment_card")
                .size(width = 208.dp, height = 124.dp)
                .clickable(onClick = { onItemClick(item) })
        ) {
            PaymentCardContainer(modifier = Modifier.fillMaxSize())
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
                            .setContentDescription("payment_card_number")
                        ,
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
                        expiredDateTransformedText(AnnotatedString(item.expiredDate)).text,
                        style = PaymentCardTextStyle,
                        modifier = Modifier.setContentDescription("payment_card_expired_date")
                    )
                }
            }
        }
    }


    @Composable
    private fun PaymentCardContainer(
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
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PaymentCardPreview1() {
    PaymentsTheme {
        PaymentCard()
    }
}

@Preview(showBackground = true)
@Composable
private fun PaymentCardPreview2() {

    val card = Card(
        number = "1111222233334444",
        expiredDate = "0421",
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