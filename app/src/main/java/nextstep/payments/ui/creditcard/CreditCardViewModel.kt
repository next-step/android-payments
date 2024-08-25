package nextstep.payments.ui.creditcard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nextstep.payments.data.PaymentCardsRepository
import nextstep.payments.model.Card
import nextstep.payments.model.CardRegisterResult

class CreditCardViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<CreditCardUiState>(CreditCardUiState.Empty)
    val uiState = _uiState.asStateFlow()

    private val _effect = MutableSharedFlow<CreditCardEffect>()
    val effect = _effect.asSharedFlow()

    init {
        fetchCards()
    }

    private fun fetchCards() {
        val cards = PaymentCardsRepository.cards
        _uiState.update {
            when (cards.size) {
                0 -> CreditCardUiState.Empty
                1 -> CreditCardUiState.One(cards.first())
                else -> CreditCardUiState.Many(cards)
            }
        }
    }

    fun handleCardRegisterResult(result: CardRegisterResult?) {
        if (result == CardRegisterResult.SUCCESS) {
            fetchCards()
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
