package nextstep.payments.data

import nextstep.payments.model.Card

object PaymentCardsRepository {
    private val _cards = mutableListOf<Card>()
    val cards: List<Card> get() = _cards.toList()

    fun addCard(card: Card) {
        _cards.add(card)
    }

    fun getCard(cardId: Int): Card? {
        return _cards.find { it.id == cardId }
    }

    fun setCard(card: Card) {
        val index = _cards.indexOfFirst { it.id == card.id }
        if (index != -1) {
            _cards[index] = card
        }
    }
}
