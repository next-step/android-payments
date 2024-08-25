package nextstep.payments.ui.card

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import nextstep.payments.data.model.Card
import nextstep.payments.data.repository.PaymentCardsRepository

class CardViewModel(
    private val repository: PaymentCardsRepository = PaymentCardsRepository
): ViewModel() {

    private val _creditCardUiState = MutableStateFlow<CreditCardUiState>(CreditCardUiState.Empty)
    val creditCardUiState: StateFlow<CreditCardUiState> = _creditCardUiState.asStateFlow()

    fun fetchCards() {
        val cards = repository.cards
        if (cards.isEmpty()) {
            _creditCardUiState.value = CreditCardUiState.Empty
        } else if (cards.size == 1) {
            _creditCardUiState.value = CreditCardUiState.One(cards[0])
        } else {
            _creditCardUiState.value = CreditCardUiState.Many(cards)
        }
    }
}

sealed interface CreditCardUiState {
    data object Empty : CreditCardUiState
    data class One(val card: Card) : CreditCardUiState
    data class Many(val cards: List<Card>): CreditCardUiState
}
