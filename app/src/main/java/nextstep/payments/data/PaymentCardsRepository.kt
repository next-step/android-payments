package nextstep.payments.data

import nextstep.payments.model.Card

object PaymentCardsRepository {

    private val _cards = mutableListOf<Card>()
    val cards: List<Card> get() = _cards.toList()

    fun addCard(card: Card) {
        val id = cards.size + 1
        _cards.add(card.copy(id = id))
    }

    fun modifyCard(card: Card) {
        val index = cards.indexOfFirst { it.id == card.id }
        _cards[index] = card.copy(id = _cards[index].id)
    }
}
