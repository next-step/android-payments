package nextstep.payments.ui.model

data class PaymentCardModel(
    val cardNumber: String,
    val ownerName: String,
    val expiredDate: String,
    val cvcNumber: String,
    val password: String
)
