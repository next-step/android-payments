package nextstep.payments.ui.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import nextstep.payments.data.CachedPaymentRepository
import nextstep.payments.domain.PaymentRepository
import nextstep.payments.ui.add.AddCardUiState.Companion.toPaymentCard

class AddCardViewModel(
    private val paymentRepository: PaymentRepository = CachedPaymentRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddCardUiState())
    val uiState: StateFlow<AddCardUiState> = _uiState.asStateFlow()

    fun addPayment() {
        viewModelScope.launch {
            paymentRepository.addPaymentCard(uiState.value.toPaymentCard())
        }
    }

    fun updateCardNumber(cardNumber: String) {
        _uiState.value = uiState.value.copy(cardNumber = cardNumber)
    }

    fun updateExpirationDate(expirationDate: String) {
        _uiState.value = uiState.value.copy(expirationDate = expirationDate)
    }

    fun updateOwnerName(ownerName: String) {
        _uiState.value = uiState.value.copy(ownerName = ownerName)
    }

    fun updateCvcNumber(cvcNumber: String) {
        _uiState.value = uiState.value.copy(cvcNumber = cvcNumber)
    }

    fun updatePassword(password: String) {
        _uiState.value = uiState.value.copy(password = password)
    }
}
