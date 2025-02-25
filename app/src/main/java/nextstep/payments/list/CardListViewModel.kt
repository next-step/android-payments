package nextstep.payments.list

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import nextstep.payments.data.CardRepository

class CardListViewModel(
    cardRepository: CardRepository = CardRepository
): ViewModel() {

    private val _uiState = MutableStateFlow<CardListState>(CardListState.Empty)
    val uiState: StateFlow<CardListState> = _uiState.asStateFlow()

    init {
        when (cardRepository.cards.size) {
            0 -> _uiState.value = CardListState.Empty
            1 -> _uiState.value = cardRepository.cards.firstOrNull()?.let { CardListState.Single(it) } ?: CardListState.Empty
            else -> _uiState.value = CardListState.Multiple(cardRepository.cards)
        }
    }
}