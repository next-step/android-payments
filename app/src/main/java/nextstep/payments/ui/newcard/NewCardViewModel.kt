package nextstep.payments.ui.newcard

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import nextstep.payments.data.PaymentCardsRepository
import nextstep.payments.model.BankType

class NewCardViewModel(
    private val repository: PaymentCardsRepository = PaymentCardsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(NewCardState())
    val uiState: StateFlow<NewCardState> = _uiState.asStateFlow()

    fun setCardNumber(cardNumber: String) {
        _uiState.update {
            it.copy(cardNumber = cardNumber.take(16))
        }
    }

    fun setExpiredDate(expiredDate: String) {
        _uiState.update {
            it.copy(expiredDate = expiredDate.take(4))
        }
    }

    fun setOwnerName(ownerName: String) {
        _uiState.update {
            it.copy(ownerName = ownerName)
        }
    }

    fun setPassword(password: String) {
        _uiState.update {
            it.copy(password = password.take(4))
        }
    }

    fun setBankType(bankType: BankType) {
        _uiState.update {
            it.copy(selectedBank = bankType)
        }
    }

    fun addCard() {
        if (repository.upsertCard(_uiState.value.toCard())) {
            _uiState.update { it.copy(cardAdded = true) }
        }
    }
}
