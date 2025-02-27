package nextstep.payments

import nextstep.payments.domain.Card
import nextstep.payments.domain.PaymentCardsRepository

class FakePaymentCardsRepository(initCards: List<Card>) : PaymentCardsRepository {
    private val cards: MutableList<Card> = initCards.toMutableList()

    override fun getCards(): List<Card> {
        return cards.toList()
    }

    override fun addCard(card: Card) {
        cards.add(card)
    }

    companion object {
        val fakeCards = listOf(
            Card(
                numbers = "1111222233334444",
                expiredDate = "0522",
                ownerName = "CREW",
                password = "0000"
            ),
            Card(
                numbers = "4444333322221111",
                expiredDate = "0421",
                ownerName = "BANDAL",
                password = "0000",
            ),
            Card(
                numbers = "1111222233444433",
                expiredDate = "0522",
                ownerName = "LACO",
                password = "0000"
            ),
            Card(
                numbers = "2211334411224455",
                expiredDate = "0421",
                ownerName = "LEAH",
                password = "0000",
            ),
            Card(
                numbers = "2211334411224454",
                expiredDate = "0421",
                ownerName = "BEOKBEOK",
                password = "0000",
            )
        )
    }
}
