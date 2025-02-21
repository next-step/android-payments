package nextstep.payments.data.repository

import nextstep.payments.data.model.CardModel

object PaymentCardsRepository {
    private val _cards = mutableListOf<CardModel>()
    val cards: List<CardModel> get() = _cards.toList()

    fun addCard(card: CardModel) {
        _cards.add(card)
    }
}