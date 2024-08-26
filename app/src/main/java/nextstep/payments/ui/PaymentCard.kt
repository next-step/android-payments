package nextstep.payments.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.data.BcCard
import nextstep.payments.data.Card
import nextstep.payments.ui.card.list.component.card.CardExpiredDate
import nextstep.payments.ui.card.list.component.card.CardNumber
import nextstep.payments.ui.card.list.component.card.CardOwnerName

@Composable
fun PaymentCard(
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
                .padding(start = 14.dp, bottom = 10.dp)
                .size(width = 40.dp, height = 26.dp)
                .background(
                    color = Color(0xFFCBBA64),
                    shape = RoundedCornerShape(4.dp),
                )
        )
    }
}

@Composable
fun PaymentCard(
    card: Card,
    cardColor: Color,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.BottomEnd,
        modifier = modifier
            .shadow(8.dp)
            .size(width = 208.dp, height = 124.dp)
            .background(
                color = cardColor,
                shape = RoundedCornerShape(5.dp),
            )
            .padding(bottom = 10.dp)
    ) {
        Column(
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

            CardNumber(
                cardNumber = card.cardNumber,
                modifier = Modifier.padding(start = 14.dp, end = 14.dp)
            )

            Row {
                CardOwnerName(
                    ownerName = card.ownerName,
                    modifier = Modifier.padding(start = 14.dp)
                )

                Spacer(modifier = Modifier.weight(1f))

                CardExpiredDate(
                    expiredDate = card.expiredDate,
                    modifier = Modifier.padding(end = 14.dp)
                )
            }
        }

    }
}

@Preview
@Composable
private fun PaymentCardPreview() {
    PaymentCard()
}

@Preview
@Composable
private fun PaymentCardInfoPreview() {
    PaymentCard(
        Card(
            cardNumber = "1234-5678-1234-5678",
            ownerName = "홍길동",
            expiredDate = "12/34",
            password = "123",
            cardCompany = BcCard
        ),
        cardColor = Color(0xff333333),
        modifier = Modifier
            .size(width = 208.dp, height = 124.dp)
    )
}
