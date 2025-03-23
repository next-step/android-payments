package nextstep.payments.ui.model

import nextstep.payments.ui.newcard.model.CardCompany

sealed class CreditCardType {

    data class AddingCard(
        val cardCompany: CardCompany,
    ) : CreditCardType()

    data class RegisteredCard(
        val number: String,
        val expiredDate: String,
        val ownerName: String,
        val password: String,
        val cardCompany: CardCompany,
    ) : CreditCardType()
}
