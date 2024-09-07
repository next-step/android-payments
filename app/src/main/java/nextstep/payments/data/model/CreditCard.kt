package nextstep.payments.data.model

data class CreditCard(
    val cardNumber: String,
    val expiredDate: String,
    val ownerName: String,
    val password: String,
    val bankType: BankType
)
