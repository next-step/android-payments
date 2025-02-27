package nextstep.payments.data

import nextstep.payments.domain.Card
import nextstep.payments.domain.PaymentCardsRepository

object InMemoryPaymentCardsRepository: PaymentCardsRepository {
    private val cards = mutableListOf<Card>()

    override fun getCards(): List<Card> {
        return cards.toList()
    }

    override fun addCard(card: Card) {
        cards.add(card)
    }
}
