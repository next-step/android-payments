package nextstep.payments.ui.editcard

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import nextstep.payments.data.model.Card
import nextstep.payments.data.repository.PaymentCardsRepository
import nextstep.payments.ui.newcard.model.BankUI

data class EditCardUiState(
    val id: Int = 0,
    val cardNumber: String = "",
    val expiredDate: String = "",
    val ownerName: String = "",
    val password: String = "",
    val bankUI: BankUI = BankUI.EMPTY,
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
    val isCardNumberFormatError =
        cardNumber.isNotEmpty() && !cardNumber.matches(Regex(cardNumberRegex))
    val isExpiredDateEmptyError = isInitialInput && expiredDate.isEmpty()
    val isExpiredDateFormatError =
        expiredDate.isNotEmpty() && !expiredDate.matches(Regex(fourDigitRegex))
    val isPasswordEmptyError = isInitialInput && password.isEmpty()
    val isPasswordFormatError = password.isNotEmpty() && !password.matches(Regex(fourDigitRegex))

    val isCardAddable =
        !isCardNumberEmptyError && !isCardNumberFormatError &&
                !isExpiredDateEmptyError && !isExpiredDateFormatError &&
                !isPasswordEmptyError && !isPasswordFormatError && isInitialInput
}

class EditCardViewModel(
    private val repository: PaymentCardsRepository = PaymentCardsRepository
) : ViewModel() {
    private val _editCardUiState = MutableStateFlow(EditCardUiState())
    val editCardUiState: StateFlow<EditCardUiState> = _editCardUiState.asStateFlow()

    private val _cardEdited = MutableStateFlow(false)
    val cardEdited: StateFlow<Boolean> = _cardEdited.asStateFlow()

    fun editCard() {
        val currentCard = repository.getCardById(_editCardUiState.value.id)
        val editCard = Card(
            id = _editCardUiState.value.id,
            cardNumber = _editCardUiState.value.cardNumber,
            expiredDate = _editCardUiState.value.expiredDate,
            ownerName = _editCardUiState.value.ownerName,
            password = _editCardUiState.value.password,
            bank = _editCardUiState.value.bankUI.toBank()
        )
        if (currentCard != null && currentCard != editCard && _editCardUiState.value.isCardAddable) {
            val card = Card(
                id = _editCardUiState.value.id,
                cardNumber = _editCardUiState.value.cardNumber,
                expiredDate = _editCardUiState.value.expiredDate,
                ownerName = _editCardUiState.value.ownerName,
                password = _editCardUiState.value.password,
                bank = _editCardUiState.value.bankUI.toBank()
            )
            repository.editCard(card)
            _cardEdited.value = true
        } else {
            _editCardUiState.update { currentState ->
                currentState.copy(isInitialInput = true)
            }
        }
    }

    fun setId(id: Int) {
        _editCardUiState.update { currentState ->
            currentState.copy(id = id)
        }
    }

    fun setCardNumber(cardNumber: String) {
        cardNumber.take(10)
        _editCardUiState.update { currentState ->
            currentState.copy(cardNumber = cardNumber.take(EditCardUiState.MAX_LENGTH_16))
        }
    }

    fun setExpiredDate(expiredDate: String) {
        _editCardUiState.update { currentState ->
            currentState.copy(expiredDate = expiredDate.take(EditCardUiState.MAX_LENGTH_4))
        }
    }

    fun setOwnerName(ownerName: String) {
        ownerName.take(3)
        _editCardUiState.update { currentState ->
            currentState.copy(ownerName = ownerName)
        }
    }

    fun setPassword(password: String) {
        _editCardUiState.update { currentState ->
            currentState.copy(password = password.take(EditCardUiState.MAX_LENGTH_4))
        }
    }

    fun setBank(bankUI: BankUI) {
        _editCardUiState.update { currentState ->
            currentState.copy(bankUI = bankUI)
        }
    }
}
