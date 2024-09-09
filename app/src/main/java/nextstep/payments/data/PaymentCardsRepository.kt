package nextstep.payments.data

import nextstep.payments.data.model.CreditCard

object PaymentCardsRepository {

    private val _creditCards = mutableListOf<CreditCard>()
    val creditCards: List<CreditCard> get() = _creditCards.toList()

    fun addCard(creditCard: CreditCard) {
        _creditCards.add(creditCard)
    }

    fun editCard(
        currentCard: CreditCard,
        updatedCard: CreditCard
    ) {
        val currentCardIndex = _creditCards.indexOf(currentCard)
        if(currentCardIndex == -1) return

        _creditCards[currentCardIndex] = updatedCard
    }
}
