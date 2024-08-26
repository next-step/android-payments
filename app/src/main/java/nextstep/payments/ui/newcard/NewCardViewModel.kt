package nextstep.payments.ui.newcard

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import nextstep.payments.data.model.Card
import nextstep.payments.data.repository.PaymentCardsRepository

data class NewCardUiState(
    val cardNumber: String = "",
    val expiredDate: String = "",
    val ownerName: String = "",
    val password: String = "",
    val isInitialInput: Boolean = false
) {

    companion object {
        // 다양한 최대 길이를 정의
        const val MAX_LENGTH_4 = 4
        const val MAX_LENGTH_16 = 16
    }

    private val cardNumberRegex = "^\\d{16}$"
    private val fourDigitRegex = "^\\d{4}$"

    val isCardNumberEmptyError = isInitialInput && cardNumber.isEmpty()
    val isCardNumberFormatError = cardNumber.isNotEmpty() && !cardNumber.matches(Regex(cardNumberRegex))
    val isExpiredDateEmptyError = isInitialInput && expiredDate.isEmpty()
    val isExpiredDateFormatError = expiredDate.isNotEmpty() && !expiredDate.matches(Regex(fourDigitRegex))
    val isPasswordEmptyError = isInitialInput && password.isEmpty()
    val isPasswordFormatError = password.isNotEmpty() && !password.matches(Regex(fourDigitRegex))

    val isCardAddable =
        !isCardNumberEmptyError && !isCardNumberFormatError &&
                !isExpiredDateEmptyError && !isExpiredDateFormatError &&
                !isPasswordEmptyError && !isPasswordFormatError && isInitialInput
}

class NewCardViewModel(
    private val repository: PaymentCardsRepository = PaymentCardsRepository
) : ViewModel() {
    private val _newCardUiState = MutableStateFlow(NewCardUiState())
    val newCardUiState: StateFlow<NewCardUiState> = _newCardUiState.asStateFlow()

    private val _cardAdded = MutableStateFlow(false)
    val cardAdded: StateFlow<Boolean> = _cardAdded.asStateFlow()

    fun addCard() {
        if (_newCardUiState.value.isCardAddable) {
            val card = Card(
                cardNumber = _newCardUiState.value.cardNumber,
                expiredDate = _newCardUiState.value.expiredDate,
                ownerName = _newCardUiState.value.ownerName,
                password = _newCardUiState.value.password
            )
            repository.addCard(card)
            _cardAdded.value = true
        } else {
            _newCardUiState.update { currentState ->
                currentState.copy(isInitialInput = true)
            }
        }
    }

    fun setCardNumber(cardNumber: String) {
        cardNumber.take(10)
        _newCardUiState.update { currentState ->
            currentState.copy(cardNumber = cardNumber.take(NewCardUiState.MAX_LENGTH_16))
        }
    }

    fun setExpiredDate(expiredDate: String) {
        _newCardUiState.update { currentState ->
            currentState.copy(expiredDate = expiredDate.take(NewCardUiState.MAX_LENGTH_4))
        }
    }

    fun setOwnerName(ownerName: String) {
        ownerName.take(3)
        _newCardUiState.update { currentState ->
            currentState.copy(ownerName = ownerName)
        }
    }

    fun setPassword(password: String) {
        _newCardUiState.update { currentState ->
            currentState.copy(password = password.take(NewCardUiState.MAX_LENGTH_4))
        }
    }
}
