package nextstep.payments.ui.register

data class PaymentRegisterUiState(
    val cardNumber: String = "",
    val expiredDate: String = "",
    val ownerName: String = "",
    val cvc: String = "",
    val password: String = "",
)
