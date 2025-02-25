package nextstep.payments.model

data class Card(
    val cardNumber: String,
    val expiredDate: String,
    val ownerName: String,
    val password: String,
)
