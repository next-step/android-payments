package nextstep.payments.repository

import nextstep.payments.model.Card


object PaymentCardsRepository {
    private val _cards = mutableListOf<Card>()
    val cards: List<Card> get() = _cards.toList()

    fun addCard(card: Card) {
        _cards.add(card)
    }

    fun modifyCard(card: Card) {
        val index = _cards.indexOfFirst { it.id == card.id }
        _cards[index] = card.copy(id = _cards[index].id)
    }
}
