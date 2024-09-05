package nextstep.payments.ui.cardlist

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import nextstep.payments.repository.PaymentCardsRepository

internal class CardListViewModel(
    private val paymentCardsRepository: PaymentCardsRepository = PaymentCardsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CardListUiState.from(paymentCardsRepository.cards))
    val uiState = _uiState.asStateFlow()

    fun fetchCards() {
        val cards = paymentCardsRepository.cards
        _uiState.update { CardListUiState.from(cards) }
    }
}