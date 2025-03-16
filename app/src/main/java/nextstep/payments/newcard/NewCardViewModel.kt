package nextstep.payments.newcard

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import nextstep.payments.PaymentCardsRepository
import nextstep.payments.R
import nextstep.payments.common.model.Bank
import nextstep.payments.newcard.model.NewCardUiState
import nextstep.payments.newcard.model.Validation

class NewCardViewModel(
    private val paymentCardsRepository: PaymentCardsRepository = PaymentCardsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(NewCardUiState())
    val uiState: StateFlow<NewCardUiState> = _uiState.asStateFlow()

    fun setBank(bank: Bank) {
        _uiState.update { prev ->
            prev.copy(
                card = prev.card.copy(bank = bank)
            )
        }
    }

    fun setCardNumber(cardNumber: String) {
        _uiState.update { prev ->
            prev.copy(
                card = prev.card.copy(cardNumber = cardNumber),
                cardNumberValidation = validateCardNumber(cardNumber)
            )
        }
    }

    private fun validateCardNumber(cardNumber: String): Validation {
        return if (cardNumber.length == 16) {
            Validation.Success
        } else {
            Validation.Error(R.string.card_number_length_error)
        }
    }

    fun setExpiredDate(expiredDate: String) {
        _uiState.update { prev ->
            prev.copy(
                card = prev.card.copy(expiredDate = expiredDate),
                expiredDateValidation = validateExpiredDate(expiredDate)
            )
        }
    }

    private fun validateExpiredDate(expiredDate: String): Validation {
        return if (expiredDate.length == 4) {
            Validation.Success
        } else {
            Validation.Error(R.string.date_length_error)
        }
    }


    fun setOwnerName(ownerName: String) {
        _uiState.update { prev ->
            prev.copy(card = prev.card.copy(ownerName = ownerName))
        }
    }

    fun setPassword(password: String) {
        _uiState.update { prev ->
            prev.copy(
                card = prev.card.copy(password = password),
                passwordValidation = validatePassword(password)
            )
        }
    }

    private fun validatePassword(password: String): Validation {
        return if (password.length == 4) {
            Validation.Success
        } else {
            Validation.Error(R.string.password_length_error)
        }
    }

    fun addCard() {
        paymentCardsRepository.addCard(_uiState.value.card)
    }
}
