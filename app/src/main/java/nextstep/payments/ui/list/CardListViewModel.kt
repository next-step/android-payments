package nextstep.payments.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import nextstep.payments.data.repository.PaymentCardsRepository

class CardListViewModel(
    private val repository: PaymentCardsRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(CardListUiState.of(repository.cards))
    val uiState: StateFlow<CardListUiState> = _uiState.asStateFlow()

    fun refresh() {
        _uiState.update {
            CardListUiState.of(repository.cards)
        }
    }

    companion object {
        fun getFactory(repository: PaymentCardsRepository) =
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return CardListViewModel(repository) as T
                }
            }
    }
}
