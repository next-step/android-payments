package nextstep.payments.ui.domain.model

import java.time.LocalDate

data class Card(
    val id: String,
    val cardNumber: String,
    val ownerName: String?,
    val expiredDate: LocalDate,
    val imageUrl: String,
)
