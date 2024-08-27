package nextstep.payments.ui.screen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import nextstep.payments.ui.state.PaymentCardUiState

class PaymentCardsViewModel : ViewModel() {
    private val _cardsScreenState = MutableStateFlow<PaymentCardUiState>(PaymentCardUiState.Empty)
    val cardsScreenState: StateFlow<PaymentCardUiState> = _cardsScreenState.asStateFlow()
}
