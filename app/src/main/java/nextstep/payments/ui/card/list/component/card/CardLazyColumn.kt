package nextstep.payments.ui.card.list.component.card

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import nextstep.payments.data.Card
import nextstep.payments.data.RegisteredCreditCards
import nextstep.payments.data.PaymentCardsRepository
import nextstep.payments.ui.PaymentCard

@Composable
fun CardLazyColumn(cards: List<Card>) {
    LazyColumn {
        items(
            count = cards.size,
            key = { index -> cards[index].cardNumber }
        ) {
            PaymentCard(
                card =  cards[it],
                cardColor = Color(0xff333333),
                modifier = Modifier.padding(
                    start = 73.dp,
                    end = 73.dp,
                    top = 12.dp,
                    bottom = 24.dp
                )
            )
        }
    }
}
