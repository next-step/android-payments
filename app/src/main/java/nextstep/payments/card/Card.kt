package nextstep.payments.card

import java.util.Date

data class Card(
    val cardNumber: String,
    val expireDate: Date,
    val ownerName: String,
    val cvcNumber: String,
    val password: String,
)
