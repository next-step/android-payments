package nextstep.payments.data

import nextstep.payments.domain.Card
import nextstep.payments.domain.CardCompany
import nextstep.payments.domain.PaymentCardsRepository

object InMemoryPaymentCardsRepository : PaymentCardsRepository {
    private val cards = mutableListOf<Card>()

    private var currentId = 0

    override fun getCards(): List<Card> {
        return cards.toList()
    }

    override fun addCard(
        numbers: String,
        expiredDate: String,
        ownerName: String,
        password: String,
        cardCompany: CardCompany,
    ) {
        val card = Card(
            id = currentId++,
            numbers = numbers,
            expiredDate = expiredDate,
            ownerName = ownerName,
            password = password,
            cardCompany = cardCompany,
        )

        cards.add(card)
    }

    override fun updateCard(card: Card) {
        cards.replaceAll {
            if (it.id == card.id) card else it
        }
    }

    override fun findCardById(cardId: Int): Card? {
        return cards.find { it.id == cardId}
    }
}
