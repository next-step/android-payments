package nextstep.payments.ui.screen.creditcard.model

import nextstep.payments.data.Card

data class CreditCard(
    val cardNumber: String,
    val cardOwnerName: String,
    val cardExpiredDate: String,
)

internal fun Card.toCreditCard() = CreditCard(
    cardNumber = cardNumber,
    cardOwnerName = cardOwnerName,
    cardExpiredDate = cardExpiredDate,
)
