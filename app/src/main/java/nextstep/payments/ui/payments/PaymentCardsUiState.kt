package nextstep.payments.ui.payments

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import nextstep.payments.domain.PaymentCard

sealed interface PaymentCardsUiState {
    data object Empty : PaymentCardsUiState
    data class One(val card: PaymentCard) : PaymentCardsUiState
    data class Many(val cards: List<PaymentCard>) : PaymentCardsUiState
}

internal class PaymentCardsUiStatePreviewParameterProvider :
    PreviewParameterProvider<PaymentCardsUiState> {
    override val values = sequenceOf(
        PaymentCardsUiState.Empty,
        PaymentCardsUiState.One(PaymentCard.MockData),
        PaymentCardsUiState.Many(
            cards = listOf(
                PaymentCard.MockData,
                PaymentCard.MockData,
                PaymentCard.MockData,
                PaymentCard.MockData
            )
        )
    )
}
