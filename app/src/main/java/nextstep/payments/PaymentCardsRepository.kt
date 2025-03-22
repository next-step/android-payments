package nextstep.payments

import nextstep.payments.common.model.Card

object PaymentCardsRepository {
    private val _cards = mutableListOf<Card>()
    val cards: List<Card> get() = _cards.toList()

    fun addCard(card: Card) {
        val id = _cards.size
        _cards.add(card.copy(id = id))
    }

    fun editCard(card: Card) {
        val index = _cards.indexOfLast { card.id == it.id }
        if (index != -1) _cards[index] = card
    }
}
