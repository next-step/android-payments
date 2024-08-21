package nextstep.payments.ui.newcard

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import nextstep.payments.model.OwnerNameValidResult

class NewCardViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(NewCardScreenUiState.NONE)
    val uiState: StateFlow<NewCardScreenUiState> = _uiState.asStateFlow()

    fun setCardNumber(cardNumber: String) {
        _uiState.update { it.copy(cardNumber = cardNumber) }
    }

    fun setExpiredDate(expiredDate: String) {
        _uiState.update { it.copy(expiredDate = expiredDate) }
    }

    fun setOwnerName(ownerName: String) {
        val result = validateOwnerName(ownerName)
        _uiState.update { it.copy(ownerName = ownerName, ownerNameValidResult = result) }
    }

    private fun validateOwnerName(ownerName: String): OwnerNameValidResult =
        when {
            ownerName.length > 30 -> OwnerNameValidResult.ERROR_OWNER_NAME_LENGTH
            else -> OwnerNameValidResult.VALID
        }

    fun setPassword(password: String) {
        _uiState.update { it.copy(password = password) }
    }
}
