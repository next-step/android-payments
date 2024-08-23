package nextstep.payments.model

data class Card(
    val id: Long = 0,
    val brand: Brand,
    val cardNumber: String,
    val expiredDate: String,
    val ownerName: String,
    val password: String,
)
