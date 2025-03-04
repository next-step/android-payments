package nextstep.payments.cardlist.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.cardlist.CardListEvent
import nextstep.payments.cardlist.CardsState
import nextstep.payments.model.Card
import nextstep.payments.ui.component.ClickablePaymentCard
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun CardListCardsContent(
    cardsStateState: CardsState.Cards,
    sendEvent: (CardListEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        item {
            Spacer(modifier = Modifier.height(12.dp))
        }

        items(
            items = cardsStateState.cards,
            key = { card -> card.id },
        ) { card ->
            ClickablePaymentCard(
                card = card,
                onClickCardItem = { sendEvent(CardListEvent.OnClickCardItem(card.id)) }
            )
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CardListContentCardsPreview() {
    PaymentsTheme {
        CardListCardsContent(
            cardsStateState = CardsState.Cards(
                listOf(
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
                )
            ),
            sendEvent = {},
        )
    }
}
