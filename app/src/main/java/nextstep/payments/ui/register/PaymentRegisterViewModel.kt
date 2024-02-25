package nextstep.payments.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import nextstep.payments.ui.data.repository.CardRepositoryImpl
import nextstep.payments.ui.domain.model.CardRegistrationForm
import nextstep.payments.ui.domain.repository.CardRepository
import java.time.LocalDate

internal class PaymentRegisterViewModel(
    private val cardRepository: CardRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(PaymentRegisterUiState())
    val uiState: StateFlow<PaymentRegisterUiState> = _uiState.asStateFlow()

    private val _isRegistered = MutableStateFlow(false)
    val isRegistered = _isRegistered.asStateFlow()

    fun registerCard() {
        viewModelScope.launch {
            val uiState = uiState.value
            val cardNumber = uiState.cardNumber
            val ownerName = uiState.ownerName
            val expiredDate = uiState.expiredDate
            val cvc = uiState.cvc
            val password = uiState.password
            if (!isValid()) {
                return@launch
            }

            val form = CardRegistrationForm(
                cardNumber = cardNumber,
                ownerName = ownerName,
                expiredDate = LocalDate.of(
                    /* year = */ expiredDate.takeLast(2).toInt(),
                    /* month = */ expiredDate.take(2).toInt(),
                    /* dayOfMonth = */ 15,
                ),
                cvc = cvc,
                password = password
            )
            cardRepository.addCard(form)
            _isRegistered.value = true
        }
    }

    private fun isValid(): Boolean {
        val uiState = uiState.value
        val cardNumber = uiState.cardNumber
        val expiredDate = uiState.expiredDate
        val cvc = uiState.cvc
        val password = uiState.password

        return when {
            cardNumber.isBlank() -> false
            expiredDate.isBlank() -> false
            cvc.isBlank() -> false
            password.isBlank() -> false
            else -> true
        }
    }

    fun updateCardNumber(cardNumber: String) {
        _uiState.value = uiState.value.copy(cardNumber = cardNumber)
    }

    fun updateExpiredDate(expiredDate: String) {
        _uiState.value = uiState.value.copy(expiredDate = expiredDate)
    }

    fun updateOwnerName(ownerName: String) {
        _uiState.value = uiState.value.copy(ownerName = ownerName)
    }

    fun updateCvc(cvc: String) {
        _uiState.value = uiState.value.copy(cvc = cvc)
    }

    fun updatePassword(password: String) {
        _uiState.value = uiState.value.copy(password = password)
    }

    class Factory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return PaymentRegisterViewModel(
                cardRepository = CardRepositoryImpl(),
            ) as T
        }
    }
}
