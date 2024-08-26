package nextstep.payments.data

data class CardData(
    val cardNumber: String,
    val cardOwnerName: String,
    val cardExpiredDate: String,
    val cardPassword: String,
    val bankType: BankTypeData
)
