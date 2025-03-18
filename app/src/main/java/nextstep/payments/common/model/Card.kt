package nextstep.payments.common.model

data class Card(
    val cardCompany: CardCompany?,
    val cardNumber: String,
    val expiredDate: String,
    val ownerName: String,
    val password: String,
)
