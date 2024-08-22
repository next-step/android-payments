package nextstep.payments.model

data class PaymentCardModel(
    val cardNumber: String,
    val expiredDate: String,
    val ownerName: String,
    val password: String,
)
