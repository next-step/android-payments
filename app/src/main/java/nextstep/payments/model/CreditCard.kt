package nextstep.payments.model

data class CreditCard(
    val cardNumber: String,
    val expiredDate: String,
    val ownerName: String,
    val password: String,
    val issuingBank: IssuingBank,
)
