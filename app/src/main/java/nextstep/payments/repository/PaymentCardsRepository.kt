package nextstep.payments.repository

import nextstep.payments.model.Card

object PaymentCardsRepository {

    private val _cards = mutableListOf<Card>()
    val cards: List<Card> get() = _cards.toList()

    private var _selectedCardIndex: Int = -1

    fun addCard(card: Card) {
        _cards.add(card)
    }

    fun selectCard(card: Card) {
        _selectedCardIndex = _cards.indexOf(card)
    }

    fun getSelectCard(): Card? {
        return _cards.getOrNull(_selectedCardIndex)
    }

    fun editCard(card: Card) {
        _selectedCardIndex.let { index -> _cards[index] = card }
    }

}
