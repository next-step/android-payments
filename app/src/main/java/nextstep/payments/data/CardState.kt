package nextstep.payments.data

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

@Stable
sealed class CardState {

    @Immutable
    data class Card(
        val cardId: Long,
        val cardNumber: String,
        val expiredDate: String,
        val ownerName: String,
        val password: String,
    ) : CardState() {
        val firstOfCardNumber: String = cardNumber.slice(0..3)
        val secondOfCardNumber: String = cardNumber.slice(4..7)
        val formattedExpiredDate: String = expiredDate.toString()
    }

    @Immutable
    data object EmptyCard : CardState()
}
