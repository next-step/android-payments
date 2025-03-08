package nextstep.payments.domain

data class Card(
    val id: Int,
    val numbers: String,
    val expiredDate: String,
    val ownerName: String,
    val password: String,
    val cardCompany: CardCompany,
)
