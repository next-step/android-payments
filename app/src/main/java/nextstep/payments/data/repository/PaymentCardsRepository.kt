package nextstep.payments.data.repository

import nextstep.payments.data.model.Card

object PaymentCardsRepository {

    private val _cards = mutableListOf<Card>()
    val cards: List<Card> get() = _cards.toList()

    fun addCard(card: Card) {
        _cards.add(card)
    }

    fun getCardById(id: Int): Card? {
        return _cards.find { it.id == id }
    }

    fun editCard(card: Card) {
        val index = cards.indexOfFirst { it.id == card.id }
        if (index != -1) {
            _cards[index] = card
        }
    }
}
