package nextstep.payments.data.repository

import nextstep.payments.data.model.Card

object PaymentCardsRepository {

    private var _id = 0

    private val _cards = mutableListOf<Card>()
    val cards: List<Card> get() = _cards.toList()

    fun addCard(card: Card) {
        _cards.add(card.copy(_id++))
    }

    fun getCardById(id: Int) = _cards.firstOrNull { it.id == id }

    fun update(card: Card) {
        val index = _cards.indexOfFirst { it.id == card.id }
        if (index >= 0) {
            _cards[index] = card
        }
    }
}
