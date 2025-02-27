package nextstep.payments.cardlist.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.cardlist.CardCount
import nextstep.payments.model.Card
import nextstep.payments.ui.component.CreateCardButton
import nextstep.payments.ui.component.PaymentCard
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun CardListContent(
    cardList: List<Card>,
    cardCountState: CardCount,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        item {
            Spacer(modifier = Modifier.height(12.dp))
        }

        items(cardList) { card ->
            PaymentCard(
                cardNumber = card.cardNumber,
                expiredDate = card.expiredDate,
                ownerName = card.ownerName,
            )
            Spacer(modifier = Modifier.height(36.dp))
        }

        if (cardCountState == CardCount.ONE_CARD)  {
            item {
                CreateCardButton(
                    onClick = {},
                    modifier = Modifier.testTag("CreateCardButton"),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CardListContentOneCardPreview() {
    PaymentsTheme {
        CardListContent(
            cardList = listOf(
                Card(
                    id = 1,
                    cardNumber = "1234-5678-1234-5678",
                    expiredDate = "12/34",
                    ownerName = "홍길동",
                    password = "1234",
                )
            ),
            cardCountState = CardCount.ONE_CARD,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CardListContentCardsPreview() {
    PaymentsTheme {
        CardListContent(
            cardList = listOf(
                Card(
                    id = 1,
                    cardNumber = "1234-5678-1234-5678",
                    expiredDate = "12/34",
                    ownerName = "홍길동",
                    password = "1234",
                ),
                Card(
                    id = 2,
                    cardNumber = "1234-5678-1234-5678",
                    expiredDate = "12/34",
                    ownerName = "홍길동",
                    password = "1234",
                ),
            ),
            cardCountState = CardCount.ONE_CARD,
        )
    }
}
