package nextstep.payments.data

import nextstep.payments.model.Card

object PaymentCardsRepository {
    private val _cards = mutableListOf<Card>()
    val cards: List<Card> get() = _cards.toList()

    fun addCard(card: Card) {
        _cards.add(card.copy(id = nextId()))
    }

    private fun nextId(): Long = _cards.maxOfOrNull { it.id }?.plus(1) ?: 1
}
