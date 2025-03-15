package nextstep.payments.data.repository

import nextstep.payments.data.model.Card

object PaymentCardsRepository {

    private val _cards = mutableListOf<Card>()
    val cards: List<Card> get() = _cards.toList()

    fun addCard(card: Card) {
        _cards.add(card)
    }

    fun updateCard(updatedCard: Card) {
        val index = _cards.indexOfFirst { it.cardId == updatedCard.cardId }
        if (index != -1) {
            _cards[index] = updatedCard
        }
    }

    fun getCardById(cardId: String): Card? {
        return _cards.find { it.cardId == cardId }
    }

    fun clearCards() {
        _cards.clear()
    }
}
