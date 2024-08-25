package nextstep.payments.data

import nextstep.payments.model.Card

object PaymentCardsRepository {
    private val _cards = mutableListOf<Card>()
    val cards: List<Card> get() = _cards.toList()

    fun addCard(card: Card): Card {
        val newCard = card.copy(id = nextId())
        _cards.add(newCard)
        return newCard
    }

    private fun nextId(): Long = _cards.maxOfOrNull { it.id }?.plus(1) ?: 1

    fun updateCard(card: Card) {
        _cards
            .indexOfFirst { it.id == card.id }
            .takeIf { it != -1 }
            ?.let { _cards[it] = card }
    }

    fun getCardById(id: Long): Card? = _cards.find { it.id == id }

    fun clear() {
        _cards.clear()
    }
}
