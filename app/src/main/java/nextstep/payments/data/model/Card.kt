package nextstep.payments.data.model

import nextstep.payments.ui.BankType

data class Card(
    val cardNumber: String,
    val expiredDate: String,
    val ownerName: String,
    val password: String,
    val bankType: BankType,
)
