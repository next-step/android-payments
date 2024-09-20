package nextstep.payments.ui.cardlist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nextstep.payments.model.card.CreditCard
import nextstep.payments.repository.PaymentCardsRepository
import nextstep.payments.ui.component.card.CardInformation

internal class CardListViewModel(
    private val paymentCardsRepository: PaymentCardsRepository = PaymentCardsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(CardListUiState.from(paymentCardsRepository.cards))
    val uiState = _uiState.asStateFlow()

    private val _editCard = MutableSharedFlow<CreditCard>()
    val editCard = _editCard.asSharedFlow()

    private val _errorFlow = MutableSharedFlow<Throwable>()
    val errorFlow = _errorFlow.asSharedFlow()

    fun fetchCards() {
        val cards = paymentCardsRepository.cards
        _uiState.update { CardListUiState.from(cards) }
    }

    fun editCard(cardInformation: CardInformation) = viewModelScope.launch {
        runCatching {
            paymentCardsRepository.cards.find { it.id == cardInformation.id }
                ?: error("Cannot Find Card")
        }.onSuccess { creditCard ->
            _editCard.emit(creditCard)
        }.onFailure { e ->
            Log.e("CardListScreen", e.stackTraceToString())
            _errorFlow.emit(e)
        }
    }
}