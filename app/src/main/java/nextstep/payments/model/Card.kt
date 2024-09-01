package nextstep.payments.model

import java.time.YearMonth

data class Card(
    val cardNumber: String,
    val expiredDate: String,
    val ownerName: String,
    val password: String,
    val cardCompany: CardCompany?
)