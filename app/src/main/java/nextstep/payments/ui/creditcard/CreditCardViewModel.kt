package nextstep.payments.ui.creditcard

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.savedstate.SavedStateRegistryOwner
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import nextstep.payments.data.PaymentCardsRepository
import nextstep.payments.ui.creditcard.navigation.ARG_SHOULD_FETCH_CARDS

class CreditCardViewModel(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _uiState = MutableStateFlow<CreditCardUiState>(CreditCardUiState.Empty)
    val uiState = _uiState.asStateFlow()

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
        _uiState.value =
            when (cards.size) {
                0 -> CreditCardUiState.Empty
                1 -> CreditCardUiState.One(cards.first())
                else -> CreditCardUiState.Many(cards)
            }
    }
}

class CreditCardViewModelFactory(
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null,
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle,
    ): T {
        if (modelClass.isAssignableFrom(CreditCardViewModel::class.java)) {
            return CreditCardViewModel(handle) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
