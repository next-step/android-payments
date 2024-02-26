package nextstep.payments.ui.payments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import nextstep.payments.data.CachedPaymentRepository
import nextstep.payments.domain.PaymentCard
import nextstep.payments.domain.PaymentRepository

class PaymentCardsViewModel(
    private val paymentRepository: PaymentRepository = CachedPaymentRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<PaymentCardsUiState>(PaymentCardsUiState.Empty)
    val uiState: StateFlow<PaymentCardsUiState> = _uiState.asStateFlow()

    init {
        loadCardPayments()
    }

    fun loadCardPayments() {
        viewModelScope.launch {
            val paymentCards = paymentRepository.getPayments()
            _uiState.value = paymentCards.toUiState()
        }
    }

    private fun List<PaymentCard>.toUiState(): PaymentCardsUiState = when {
        this.isEmpty() -> PaymentCardsUiState.Empty
        this.size == 1 -> PaymentCardsUiState.One(this.first())
        else -> PaymentCardsUiState.Many(this)
    }
}
