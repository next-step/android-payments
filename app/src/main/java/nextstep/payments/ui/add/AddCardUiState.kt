package nextstep.payments.ui.add

import nextstep.payments.domain.PaymentCard
import java.util.UUID

data class AddCardUiState(
    val cardNumber: String = "",
    val ownerName: String = "",
    val expirationDate: String = "",
    val cvcNumber: String = "",
    val password: String = "",
) {
    companion object {
        fun AddCardUiState.toPaymentCard(): PaymentCard = PaymentCard(
            UUID.randomUUID().toString(),
            cardNumber,
            ownerName,
            expirationDate,
            cvcNumber,
            password
        )
    }
}
