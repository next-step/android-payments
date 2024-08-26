package nextstep.payments.ui.screen.creditcard.model

import nextstep.payments.data.CardData
import nextstep.payments.ui.screen.newcard.model.BankTypeModel
import nextstep.payments.ui.screen.newcard.model.toUiModel

data class CardModel(
    val cardNumber: String,
    val cardOwnerName: String,
    val cardExpiredDate: String,
    val bankType: BankTypeModel
)

internal fun CardData.toUiModel() = CardModel(
    cardNumber = cardNumber,
    cardOwnerName = cardOwnerName,
    cardExpiredDate = cardExpiredDate,
    bankType = bankType.toUiModel()
)
