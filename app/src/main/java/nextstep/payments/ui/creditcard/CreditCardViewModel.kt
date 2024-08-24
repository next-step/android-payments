package nextstep.payments.ui.creditcard

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nextstep.payments.data.PaymentCardsRepository
import nextstep.payments.model.Card
import nextstep.payments.ui.creditcard.navigation.ARG_SHOULD_FETCH_CARDS

class CreditCardViewModel(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _uiState = MutableStateFlow<CreditCardUiState>(CreditCardUiState.Empty)
    val uiState = _uiState.asStateFlow()

    private val _effect = MutableSharedFlow<CreditCardEffect>()
    val effect = _effect.asSharedFlow()

    val shouldFetchCards =
        flow {
            emit(
                savedStateHandle.get<Boolean>(ARG_SHOULD_FETCH_CARDS) ?: false,
            )
        }.stateIn(
            viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false,
        )

    init {
        fetchCards()
    }

    fun fetchCards() {
        val cards = PaymentCardsRepository.cards
        _uiState.update {
            when (cards.size) {
                0 -> CreditCardUiState.Empty
                1 -> CreditCardUiState.One(cards.first())
                else -> CreditCardUiState.Many(cards)
            }
        }
    }

    fun dispatchEvent(event: CreditCardEvent) {
        when (event) {
            is CreditCardEvent.OnNewCardClick -> {
                navigateToRegisterCard()
            }

            is CreditCardEvent.OnCardClick -> {
                navigateToRegisterCard(event.card)
            }
        }
    }

    private fun navigateToRegisterCard(card: Card? = null) {
        viewModelScope.launch {
            val id = card?.id?.toString()
            _effect.emit(CreditCardEffect.NavigateToRegisterCard(id))
        }
    }
}
