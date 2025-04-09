package nextstep.payments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import nextstep.payments.model.CreditCard
import nextstep.payments.ui.CreditCardUiState

class PaymentCardViewModel(
    private val repository: PaymentCardsRepository = PaymentCardsRepository,
) : ViewModel() {
    val cardUiState: StateFlow<CreditCardUiState> = repository.cards.map { cards ->
        if (cards.isEmpty()) {
            CreditCardUiState.Empty
        } else if (cards.size == 1) {
            CreditCardUiState.One(cards.first() as CreditCard)
        } else {
            CreditCardUiState.Many(cards.map { it as CreditCard })
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(10_000),
        initialValue = CreditCardUiState.Empty
    )
}