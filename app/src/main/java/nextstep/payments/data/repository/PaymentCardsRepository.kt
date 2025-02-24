package nextstep.payments.data.repository

import nextstep.payments.model.CreditCard

object PaymentCardsRepository {

    private val _Credit_cards = mutableListOf<CreditCard>()
    private val creditCards: List<CreditCard> get() = _Credit_cards.toList()

    fun addCard(creditCard: CreditCard) {
        _Credit_cards.add(creditCard)
    }

    fun getCardList(): List<CreditCard> {
        return creditCards
    }
}
