package nextstep.payments.ui.screen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import nextstep.payments.domain.PaymentCardsRepository
import nextstep.payments.ui.state.PaymentCardUiState

class PaymentCardsViewModel(
    private val paymentCardsRepository: PaymentCardsRepository = PaymentCardsRepository
) : ViewModel() {
    private val _cardsScreenState = MutableStateFlow<PaymentCardUiState>(PaymentCardUiState.Empty)
    val cardsScreenState: StateFlow<PaymentCardUiState> = _cardsScreenState.asStateFlow()

    fun loadCardPayments() {
        when {
            paymentCardsRepository.cards.isEmpty() -> _cardsScreenState.value = PaymentCardUiState.Empty
            paymentCardsRepository.cards.count() == 1 -> _cardsScreenState.value = PaymentCardUiState.One(paymentCardsRepository.cards.first())
            else -> _cardsScreenState.value = PaymentCardUiState.Many(paymentCardsRepository.cards)
        }
    }
}
