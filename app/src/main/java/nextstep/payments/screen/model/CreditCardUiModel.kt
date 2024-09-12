package nextstep.payments.screen.model

import nextstep.payments.data.model.CreditCard

data class CreditCardUiModel(
    val cardNumber: String,
    val firstCardDigits: String,
    val secondCardDigits: String,
    val month: String,
    val year: String,
    val ownerName: String,
    val password: String,
    val bankTypeUiModel: BankTypeUiModel?
)

fun CreditCard.toUiModel() =
    CreditCardUiModel(
        firstCardDigits = if (cardNumber.length >= 4) {
            cardNumber.substring(0, 4)
        } else {
            cardNumber
        },
        secondCardDigits = if (cardNumber.length >= 8) {
            cardNumber.substring(4, 8)
        } else {
            cardNumber.substring(4, cardNumber.length)
        },
        cardNumber = cardNumber,
        month = if (expiredDate.length >= 2) {
            expiredDate.substring(0, 2)
        } else {
            expiredDate
        },
        year = if (expiredDate.length >= 4) {
            expiredDate.substring(2, 4)
        } else {
            expiredDate.substring(2, expiredDate.length)
        },
        ownerName = ownerName,
        password = password,
        bankTypeUiModel = bankType.toUiModel()
    )

fun CreditCardUiModel.toModel()
    = CreditCard(
        cardNumber = cardNumber,
        ownerName = ownerName,
        password = password,
        bankType = bankTypeUiModel.toEntity(),
        expiredDate = month + year
    )
