package nextstep.payments.ui.card.list.component.card

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import nextstep.payments.data.BankType
import nextstep.payments.data.Card
import nextstep.payments.ui.PaymentCard

@Composable
fun CardLazyColumn(cards: List<Card>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
    ) {
        items(
            count = cards.size,
            key = { index -> cards[index].cardNumber }
        ) {
            PaymentCard(
                card = cards[it],
                modifier = Modifier,
                content = {
                    PaymentCard(
                        brandColor = cards[it].brandColor
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
                cardNumber = "1234-5678-1234-5678",
                ownerName = "홍길동",
                expiredDate = "12/34",
                password = "123",
                brandColor = colorResource(id = BankType.KAKAO.brandColor)
            )
        )
    )
}
