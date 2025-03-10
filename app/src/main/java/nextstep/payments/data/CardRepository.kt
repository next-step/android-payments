package nextstep.payments.data

import nextstep.payments.model.CreditCard

object CardRepository {

    private val _cards = mutableListOf<CreditCard>()
    val cards: List<CreditCard> get() = _cards.toList()

    fun addCard(card: CreditCard) {
        _cards.add(card)
    }

    fun editCard(card: CreditCard) {
        val targetCard = _cards.find { it.id == card.id } ?: return
        val index = _cards.indexOf(targetCard)
        _cards[index] = card
    }

    fun getCard(cardId: String): CreditCard {
        return _cards.find { it.id == cardId } ?: CreditCard.emptyCard
    }

}
