package nextstep.payments.data

import java.util.UUID

data class CardData(
    val id: String = UUID.randomUUID().toString(),
    val cardNumber: String,
    val cardOwnerName: String,
    val cardExpiredDate: String,
    val cardPassword: String,
    val bankType: BankTypeData?
)
