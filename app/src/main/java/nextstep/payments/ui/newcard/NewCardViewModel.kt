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
    val password: String = "",
    val isInitialInput: Boolean = false
) {

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

    private val _cardNumber = MutableStateFlow("")
    val cardNumber: StateFlow<String> = _cardNumber.asStateFlow()

    private val _expiredDate = MutableStateFlow("")
    val expiredDate: StateFlow<String> = _expiredDate.asStateFlow()

    private val _ownerName = MutableStateFlow("")
    val ownerName: StateFlow<String> = _ownerName.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    fun addCard() {
        if (_newCardUiState.value.isCardAddable) {
            val card = Card(
                cardNumber = _cardNumber.value,
                expiredDate = _ownerName.value,
                ownerName = _expiredDate.value,
                password = _password.value
            )
            repository.addCard(card)
            _cardAdded.value = true
        } else {
            _newCardUiState.value = NewCardUiState(
                cardNumber = _cardNumber.value,
                expiredDate = _expiredDate.value,
                password = _password.value,
                isInitialInput = true
            )
        }
    }

    fun setCardNumber(cardNumber: String) {
        _cardNumber.value = cardNumber
        _newCardUiState.update { currentState ->
            currentState.copy(cardNumber = cardNumber)
        }
    }

    fun setExpiredDate(expiredDate: String) {
        _expiredDate.value = expiredDate
        _newCardUiState.update { currentState ->
            currentState.copy(expiredDate = expiredDate)
        }
    }

    fun setOwnerName(ownerName: String) {
        _ownerName.value = ownerName
    }

    fun setPassword(password: String) {
        _password.value = password
        _newCardUiState.update { currentState ->
            currentState.copy(password = password)
        }
    }
}
