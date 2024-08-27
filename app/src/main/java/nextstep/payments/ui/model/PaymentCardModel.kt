package nextstep.payments.ui.model

data class PaymentCardModel(
    val cardNumber: String,
    val ownerName: String,
    val expiredDate: String,
    val password: String
)
