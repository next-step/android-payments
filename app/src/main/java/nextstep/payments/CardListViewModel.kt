package nextstep.payments

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import nextstep.payments.model.CardUiState

class CardListViewModel(
    private val cardsRepository: PaymentCardsRepository = PaymentCardsRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<CardUiState>(CardUiState.Empty)
    val uiState: StateFlow<CardUiState> = _uiState.asStateFlow()

    fun fetchCards() {
        val size = cardsRepository.cards.size
        _uiState.value = when (size) {
            0 -> CardUiState.Empty
            1 -> CardUiState.One(cardsRepository.cards.first())
            else -> CardUiState.Many(cardsRepository.cards)
        }
    }
}
