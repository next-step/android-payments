package nextstep.payments.repository

import nextstep.payments.model.Card

object PaymentCardsRepository {
    private val _cards = mutableListOf<Card>()
    val cards: List<Card> get() = _cards.toList()

    fun addCard(card: Card) {
        _cards.add(card)
    }

    fun updateCard(cardId: Int, card: Card) {
        val index = _cards.indexOfFirst { it.id == cardId }
        if (index != -1) {
            _cards[index] = card
        }
    }
}
