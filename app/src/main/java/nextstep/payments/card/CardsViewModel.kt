package nextstep.payments.card

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nextstep.payments.card.add.CardsScreenUiState

class CardsViewModel(
    private val cardRepository: CardRepository = DefaultCardRepository,
) : ViewModel() {

    private val _cardsScreenUiState: MutableStateFlow<CardsScreenUiState> =
        MutableStateFlow(getCurrentCardsScreenUiState())
    val cardsScreenUiState: StateFlow<CardsScreenUiState> = _cardsScreenUiState.asStateFlow()

    fun updateCards() {
        viewModelScope.launch {
            _cardsScreenUiState.update { getCurrentCardsScreenUiState() }
        }
    }

    private fun getCurrentCardsScreenUiState(): CardsScreenUiState {
        return resolveCardsScreenUiState(cardRepository.getAllCards())
    }

    private fun resolveCardsScreenUiState(cards: List<Card>): CardsScreenUiState {
        return CardsScreenUiState(cards)
    }
}
