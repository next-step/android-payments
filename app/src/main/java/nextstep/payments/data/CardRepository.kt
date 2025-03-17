package nextstep.payments.data

import nextstep.payments.model.CreditCard

object CardRepository {

    private val _cards = mutableListOf<CreditCard>()
    val cards: List<CreditCard> get() = _cards.toList()

    fun addCard(card: CreditCard) {
        _cards.add(card)
    }

    fun editCard(card: CreditCard) {
        val index = _cards.indexOfFirst { it.id == card.id }
        if (index >= 0) {
            _cards[index] = card
        }
    }

    fun getCard(cardId: String): CreditCard {
        return _cards.find { it.id == cardId } ?: CreditCard.emptyCard
    }

}
