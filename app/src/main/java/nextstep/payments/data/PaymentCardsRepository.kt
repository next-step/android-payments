package nextstep.payments.data

import nextstep.payments.data.model.CreditCard

object PaymentCardsRepository {

    private val _creditCards = mutableListOf<CreditCard>()
    val creditCards: List<CreditCard> get() = _creditCards.toList()

    fun addCard(creditCard: CreditCard) {
        _creditCards.add(creditCard)
    }
}