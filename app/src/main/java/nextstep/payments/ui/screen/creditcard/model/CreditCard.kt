package nextstep.payments.ui.screen.creditcard.model

import nextstep.payments.data.Card
import nextstep.payments.ui.screen.newcard.model.BankTypeModel
import nextstep.payments.ui.screen.newcard.model.toUiModel

data class CreditCard(
    val cardNumber: String,
    val cardOwnerName: String,
    val cardExpiredDate: String,
    val bankType: BankTypeModel
)

internal fun Card.toCreditCard() = CreditCard(
    cardNumber = cardNumber,
    cardOwnerName = cardOwnerName,
    cardExpiredDate = cardExpiredDate,
    bankType = bankType.toUiModel()
)
