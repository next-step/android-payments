package nextstep.payments.ui.card.list.component.card

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import nextstep.payments.data.BankType
import nextstep.payments.data.Card
import nextstep.payments.ui.PaymentCard
import nextstep.payments.ui.PaymentCardContents

@Composable
fun CardLazyColumn(
    cards: List<Card>,
    modifier: Modifier = Modifier,
    onCardClick: (Card) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(
            count = cards.size,
            key = { index -> cards[index].cardNumber }
        ) {
            PaymentCard(
                brandColor = colorResource(id = cards[it].bankType.brandColor),
                modifier = Modifier,
                content = {
                    PaymentCardContents(
                        card = cards[it],
                        onClick = onCardClick
                    )
                }
            )
        }
    }
}

@Preview
@Composable
private fun CardLazyColumnPreview() {
    CardLazyColumn(
        cards = listOf(
            Card(
                id = 1,
                cardNumber = "1234-5678-1234-5678",
                ownerName = "홍길동",
                expiredDate = "12/34",
                password = "123",
                bankType = BankType.KAKAO
            )
        )
    )
}
