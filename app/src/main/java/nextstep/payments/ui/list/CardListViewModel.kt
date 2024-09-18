package nextstep.payments.ui.list

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import nextstep.payments.data.PaymentCardsRepository

class CardListViewModel(
    private val repository: PaymentCardsRepository = PaymentCardsRepository
) : ViewModel() {

    private val _creditCardState = MutableStateFlow<CreditCardUiState>(CreditCardUiState.Empty)
    val creditCardState: StateFlow<CreditCardUiState> = _creditCardState.asStateFlow()

    init {
        fetchCards()
    }

    fun fetchCards() {
        val cards = repository.cards
        _creditCardState.value = when {
            cards.isEmpty() -> CreditCardUiState.Empty
            cards.size == 1 -> CreditCardUiState.One(cards[0])
            else -> CreditCardUiState.Many(cards)
        }
    }
}
