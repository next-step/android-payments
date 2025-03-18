package nextstep.payments.data.repository

import nextstep.payments.ui.model.CreditCardType.CardInfo

object PaymentCardsRepository {

    private val _creditCardInfo = mutableListOf<CardInfo>()
    val creditCardInfo: List<CardInfo> get() = _creditCardInfo.toList()

    fun addCard(creditCard: CardInfo) {
        _creditCardInfo.add(creditCard)
    }

    fun getCardList(): List<CardInfo> {
        return _creditCardInfo
    }
}
