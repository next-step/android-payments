package nextstep.payments.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import nextstep.payments.model.Card
import nextstep.payments.repository.PaymentCardsRepository

class CardsViewModel(private val repository: PaymentCardsRepository = PaymentCardsRepository) :
    ViewModel() {
    private val _cards = MutableStateFlow<List<Card>>(emptyList())
    val cards: StateFlow<List<Card>> = _cards

    private val _cardAdded = MutableStateFlow<Boolean>(false)
    val cardAdded: StateFlow<Boolean> = _cardAdded.asStateFlow()

    fun fetchCards() {
        viewModelScope.launch {
            _cards.value = repository.cards
        }
    }

    fun notifyCardAdded() {
        _cardAdded.value = true
    }
}
