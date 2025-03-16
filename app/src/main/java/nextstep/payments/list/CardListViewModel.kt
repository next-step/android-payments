package nextstep.payments.list

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import nextstep.payments.data.CardRepository

class CardListViewModel(
    private val cardRepository: CardRepository = CardRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<CardListState>(CardListState.Empty)
    val uiState: StateFlow<CardListState> = _uiState.asStateFlow()

    fun fetchCards() {
        _uiState.update {
            when (cardRepository.cards.size) {
                0 -> CardListState.Empty
                1 -> cardRepository.cards.firstOrNull()?.let { CardListState.Single(it) } ?: CardListState.Empty
                else -> CardListState.Multiple(cardRepository.cards)
            }
        }
    }
}