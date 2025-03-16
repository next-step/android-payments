package nextstep.payments.common.model

data class Card(
    val bank: Bank?,
    val cardNumber: String,
    val expiredDate: String,
    val ownerName: String,
    val password: String,
)
