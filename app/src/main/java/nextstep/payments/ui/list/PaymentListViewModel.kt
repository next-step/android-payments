package nextstep.payments.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import nextstep.payments.ui.domain.repository.CardRepository

internal class PaymentListViewModel(
    private val cardRepository: CardRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(PaymentListUiState(emptyList()))
    val uiState: StateFlow<PaymentListUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val cards = cardRepository.getCardList()
            _uiState.value = PaymentListUiState(cards)
        }
    }
}
