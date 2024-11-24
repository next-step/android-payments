package nextstep.payments.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nextstep.payments.model.Card
import nextstep.payments.model.CardUiState
import nextstep.payments.repository.PaymentCardsRepository

class CardsViewModel(private val repository: PaymentCardsRepository = PaymentCardsRepository) :
    ViewModel() {
    private val _cards = MutableStateFlow<List<Card>>(emptyList())
    val cards: StateFlow<List<Card>> = _cards

    private val _uiState = MutableStateFlow<CardUiState>(CardUiState.Empty)
    val uiState: StateFlow<CardUiState> = _uiState.asStateFlow()

    fun fetchCards() {
        viewModelScope.launch {
            _cards.value = repository.cards
        }
    }

    fun updateCardUiState() {
        _uiState.update { currentState ->
            when (currentState) {
                is CardUiState.Empty -> {
                    if (_cards.value.isNotEmpty()) {
                        CardUiState.One(_cards.value.first())
                    } else {
                        CardUiState.Empty
                    }
                }

                is CardUiState.One -> {
                    if (_cards.value.size > 1) {
                        CardUiState.Many(_cards.value)
                    } else {
                        CardUiState.One(_cards.value.first())
                    }
                }

                is CardUiState.Many -> CardUiState.Many(_cards.value)
            }
        }
    }
}
