package nextstep.payments.domain

import nextstep.payments.ui.model.PaymentCardModel

object PaymentCardsRepository {

    private val _cards = mutableListOf<PaymentCardModel>()
    val cards: List<PaymentCardModel> get() = _cards.toList()

    fun addCard(card: PaymentCardModel) {
        _cards.add(card)
    }
}
