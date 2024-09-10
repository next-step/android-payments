package nextstep.payments.data

import androidx.compose.runtime.Stable

@Stable
data class Card(
    val cardId: Long,
    val cardNumber: String,
    val expiredDate: String,
    val ownerName: String,
    val password: String,
)
