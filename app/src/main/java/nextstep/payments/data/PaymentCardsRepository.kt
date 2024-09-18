package nextstep.payments.data

import nextstep.payments.data.model.CreditCard

object PaymentCardsRepository {

    private val _cards = mutableListOf<CreditCard>()
    val cards: List<CreditCard> get() = _cards.toList()

    fun addCard(card: CreditCard) {
        _cards.add(card)
    }
}
