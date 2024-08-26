package nextstep.payments.ui.provider

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import nextstep.payments.data.model.Card
import nextstep.payments.ui.card.CreditCardUiState

class CarListScreenProvider: PreviewParameterProvider<CreditCardUiState> {

    private val card = Card(
        cardNumber = "1234123412341234",
        expiredDate = "1234",
        ownerName = "User",
        password = "1234"
    )
    override val values: Sequence<CreditCardUiState> = sequenceOf(
        CreditCardUiState.Empty,
        CreditCardUiState.One(card),
        CreditCardUiState.Many(listOf(card, card))
    )
}
