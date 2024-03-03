package nextstep.payments.domain

data class PaymentCard(
    val id: String,
    val cardNumber: String,
    val ownerName: String,
    val expirationDate: String,
    val cvcNumber: String,
    val password: String
)
