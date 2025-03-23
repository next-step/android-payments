package nextstep.payments.model

import nextstep.payments.ui.paymentcards.Card

data class CreditCard(
    override val cardNumber: String,
    override val expiredDate: String,
    override val ownerName: String,
    override val password: String
) : Card {
    override fun getFormattedCardNumber(): String {
        return cardNumber.chunked(4).joinToString("-")
    }

    override fun getFormattedExpiredDate(): String {
        return expiredDate.chunked(2).joinToString("/")
    }
}