package nextstep.payments.repository

import nextstep.payments.model.Card

object PaymentCardsRepository {

    private val _cards = mutableListOf<Card>()
    val cards: List<Card> get() = _cards.toList()

    fun addCard(card: Card) {
        _cards.add(card)
    }

    fun setCard(cards: List<Card>) {
        _cards.clear()
        _cards.addAll(cards)
    }
}
