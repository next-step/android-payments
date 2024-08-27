package nextstep.payments.model


data class Card(
    val cardNumber: String,
    val cardOwnerName: String,
    val cardExpiredDate: String
)
