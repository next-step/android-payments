package nextstep.payments.ui.view.cardlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import nextstep.payments.data.PaymentCardRepository

class CardListViewModel : ViewModel() {
    val uiState: StateFlow<CardListUiState> = PaymentCardRepository.cardsStream
        .map {
            when {
                it.isEmpty() -> CardListUiState.Empty
                it.size == 1 -> CardListUiState.One(it.first())
                else -> CardListUiState.Many(it)
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = CardListUiState.Empty
        )
}
