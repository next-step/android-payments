package nextstep.payments.ui.screen.creditcard.model

import nextstep.payments.data.CardData
import nextstep.payments.ui.screen.newcard.model.BankTypeModel
import nextstep.payments.ui.screen.newcard.model.toData
import nextstep.payments.ui.screen.newcard.model.toUiModel

data class CardModel(
    val id: String,
    val cardNumber: String,
    val cardOwnerName: String,
    val cardExpiredDate: String,
    val cardPassword: String,
    val bankType: BankTypeModel?
)

internal fun CardData.toUiModel() = CardModel(
    id = id,
    cardNumber = cardNumber,
    cardOwnerName = cardOwnerName,
    cardExpiredDate = cardExpiredDate,
    cardPassword = cardPassword,
    bankType = bankType?.toUiModel()
)

internal fun CardModel.toData() = CardData(
    id = id,
    cardPassword = cardPassword,
    cardNumber = cardNumber,
    cardOwnerName = cardOwnerName,
    cardExpiredDate = cardExpiredDate,
    bankType = bankType?.toData()
)
