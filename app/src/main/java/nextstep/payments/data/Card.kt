package nextstep.payments.data

data class Card(
    val cardNumber: String,
    val cardOwnerName: String,
    val cardExpiredDate: String,
    val cardPassword: String,
    val bankType: BankType
)
