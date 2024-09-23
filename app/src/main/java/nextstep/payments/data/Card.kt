package nextstep.payments.data

import androidx.compose.runtime.Stable

@Stable
data class Card(
    val cardId: Long,
    val cardNumber: String,
    val expiredDate: String,
    val ownerName: String,
    val password: String,
) {
    val firstOfCardNumber: String = cardNumber.slice(0..3)
    val secondOfCardNumber: String = cardNumber.slice(4..7)
    val formattedExpiredDate: String = expiredDate.toString()
}
