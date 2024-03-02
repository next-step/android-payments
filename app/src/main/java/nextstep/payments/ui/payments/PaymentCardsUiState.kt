package nextstep.payments.ui.payments

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import nextstep.payments.domain.PaymentCard

sealed interface PaymentCardsUiState {
    data object Empty : PaymentCardsUiState
    data class One(val card: PaymentCard) : PaymentCardsUiState
    data class Many(val cards: List<PaymentCard>) : PaymentCardsUiState

    companion object {
        fun from(cards: List<PaymentCard>): PaymentCardsUiState {
            return when {
                cards.isEmpty() -> Empty
                cards.size == 1 -> One(cards.first())
                else -> Many(cards)
            }
        }
    }
}

internal class PaymentCardsUiStatePreviewParameterProvider :
    PreviewParameterProvider<PaymentCardsUiState> {
    override val values = sequenceOf(
        PaymentCardsUiState.Empty,
        PaymentCardsUiState.One(mockPaymentCard()),
        PaymentCardsUiState.Many(
            cards = listOf(
                mockPaymentCard(),
                mockPaymentCard(),
                mockPaymentCard(),
                mockPaymentCard(),
            )
        )
    )
}

internal fun mockPaymentCard(
    id: String = "1",
    cardNumber: String = "1111222233334444",
    ownerName: String = "Namjackson",
    expirationDate: String = "0425",
    cvcNumber: String = "123",
    password: String = "1234"
): PaymentCard {
    return PaymentCard(
        id = id,
        cardNumber = cardNumber,
        ownerName = ownerName,
        expirationDate = expirationDate,
        cvcNumber = cvcNumber,
        password = password
    )
}
