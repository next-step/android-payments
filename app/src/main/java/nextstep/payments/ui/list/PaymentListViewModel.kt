package nextstep.payments.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import nextstep.payments.ui.data.repository.CardRepositoryImpl
import nextstep.payments.ui.domain.repository.CardRepository

internal class PaymentListViewModel(
    private val cardRepository: CardRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow<PaymentListUiState>(PaymentListUiState.Empty)
    val uiState: StateFlow<PaymentListUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val cards = cardRepository.getCardList()

            _uiState.value = when {
                cards.isEmpty() -> PaymentListUiState.Empty
                cards.size == 1 -> PaymentListUiState.One(cards.first())
                else -> PaymentListUiState.Many(cards)
            }
        }
    }

    class Factory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return PaymentListViewModel(CardRepositoryImpl()) as T
        }
    }
}
