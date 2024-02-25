package nextstep.payments.card

data class Card(
    val cardNumber: String,
    val expireDate: String,
    val ownerName: String,
    val cvcNumber: String,
    val password: String,
)
