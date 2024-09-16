package nextstep.payments.ui.card.list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import nextstep.payments.data.PaymentCardsRepository
import nextstep.payments.data.RegisteredCreditCards

class CardListViewModel(
    val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _registeredCreditCards = MutableStateFlow(RegisteredCreditCards(emptyList()))
    val registeredCreditCards: StateFlow<RegisteredCreditCards> =
        _registeredCreditCards.asStateFlow()

    fun fetchCards() {
        _registeredCreditCards.value = RegisteredCreditCards(PaymentCardsRepository.cards)
    }

    fun saveCard() {
        savedStateHandle[KEY_REGISTERED_CREDIT_CARDS] = _registeredCreditCards.value
    }

    fun openCard() {
        _registeredCreditCards.value =
            savedStateHandle[KEY_REGISTERED_CREDIT_CARDS] ?: RegisteredCreditCards(emptyList())
    }

    companion object {
        private const val KEY_REGISTERED_CREDIT_CARDS = "registeredCreditCards"
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val savedStateHandle = createSavedStateHandle()
                CardListViewModel(
                    savedStateHandle = savedStateHandle
                )
            }
        }
    }
}
