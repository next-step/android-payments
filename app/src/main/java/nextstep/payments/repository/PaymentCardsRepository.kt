package nextstep.payments.repository

import nextstep.payments.data.BankType
import nextstep.payments.data.Card

object PaymentCardsRepository {
    private val _cards = mutableListOf<Card>()
    val cards: List<Card> get() = _cards.toList()

    fun addCard(
        cardNumber: String,
        expiredDate: String,
        ownerName: String,
        password: String,
        bankType: BankType
    ) {
        val card = Card(
            id = _cards.size,
            cardNumber = cardNumber,
            expiredDate = expiredDate,
            ownerName = ownerName,
            password = password,
            bankType = bankType
        )

        _cards.add(card)
    }

    fun updateCard(card: Card) {
        val index = _cards.indexOfFirst { it.id == card.id }
        _cards[index] = card
    }

    fun getCard(id: Int): Card {
        return _cards.first { it.id == id }
    }
}
