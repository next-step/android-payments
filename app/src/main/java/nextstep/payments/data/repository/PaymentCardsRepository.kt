package nextstep.payments.data.repository

import nextstep.payments.ui.model.CreditCardType.RegisteredCard

object PaymentCardsRepository {

    private val _creditRegisteredCard = mutableListOf<RegisteredCard>()
    val creditRegisteredCard: List<RegisteredCard> get() = _creditRegisteredCard.toList()

    fun addCard(creditCard: RegisteredCard) {
        _creditRegisteredCard.add(creditCard)
    }

    fun getCardList(): List<RegisteredCard> {
        return _creditRegisteredCard
    }
}
