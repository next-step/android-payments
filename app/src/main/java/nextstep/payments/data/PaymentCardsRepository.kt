package nextstep.payments.data

import nextstep.payments.data.model.CreditCard

object PaymentCardsRepository {

    private val _Credit_cards = mutableListOf<CreditCard>()
    val creditCards: List<CreditCard> get() = _Credit_cards.toList()

    fun addCard(creditCard: CreditCard) {
        _Credit_cards.add(creditCard)
    }
}