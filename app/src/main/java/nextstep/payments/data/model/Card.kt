package nextstep.payments.data.model

import nextstep.payments.ui.CardCompanyType

data class Card(
    val cardNumber: String,
    val expiredDate: String,
    val ownerName: String,
    val password: String,
    val cardCompanyType: CardCompanyType,
)
