package nextstep.payments.screens.card.list

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CardListViewModel: ViewModel() {
    private val _uiState: MutableStateFlow<CardListUiState> = MutableStateFlow(CardListUiState.Empty)
    val uiState: StateFlow<CardListUiState> = _uiState.asStateFlow()
}
