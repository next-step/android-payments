package nextstep.payments.repository

import nextstep.payments.model.PaymentCard

object PaymentCardsRepository {

    private val _cards = mutableListOf<PaymentCard>()
    val cards: List<PaymentCard> get() = _cards.toList()

    fun addCard(card: PaymentCard) {
        _cards.add(card)
    }
}
