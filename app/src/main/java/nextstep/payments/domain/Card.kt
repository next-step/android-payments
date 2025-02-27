package nextstep.payments.domain

data class Card(
    val numbers: String,
    val expiredDate: String,
    val ownerName: String,
    val password: String,
)
