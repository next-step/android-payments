package nextstep.payments.ui.domain.model

import java.time.LocalDate

data class CardRegistrationForm(
    val cardNumber: String,
    val expiredDate: LocalDate,
    val ownerName: String?,
    val cvc: String,
    val password: String,
)
