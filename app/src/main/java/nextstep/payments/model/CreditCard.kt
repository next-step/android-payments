package nextstep.payments.model

data class CreditCard(
    val number: String,
    val expiredDate: String,
    val ownerName: String,
    val password: String
)
