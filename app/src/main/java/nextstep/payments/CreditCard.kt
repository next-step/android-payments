package nextstep.payments

import nextstep.payments.ui.paymentcards.Card

data class CreditCard(
    override val cardNumber: String,
    override val expiredDate: String,
    override val ownerName: String,
    override val password: String
) : Card