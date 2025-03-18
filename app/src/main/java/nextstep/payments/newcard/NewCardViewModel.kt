package nextstep.payments.newcard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nextstep.payments.PaymentCardsRepository
import nextstep.payments.R
import nextstep.payments.common.model.CardCompany
import nextstep.payments.newcard.model.NewCardEvent
import nextstep.payments.newcard.model.NewCardUiState
import nextstep.payments.newcard.model.Validation

class NewCardViewModel(
    private val paymentCardsRepository: PaymentCardsRepository = PaymentCardsRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(NewCardUiState())
    val uiState: StateFlow<NewCardUiState> = _uiState.asStateFlow()

    private val _event = MutableSharedFlow<NewCardEvent>()
    val event = _event.asSharedFlow()

    fun setCardCompany(cardCompany: CardCompany) {
        _uiState.update { prev ->
            prev.copy(
                card = prev.card.copy(cardCompany = cardCompany),
                cardCompanyValidation = Validation.Success
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
        return when {
            cardNumber.length == 16 -> Validation.Success
            cardNumber.isEmpty() -> Validation.Failure.Empty
            else -> Validation.Failure.Error(R.string.card_number_length_error)
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
        return when {
            expiredDate.length == 4 -> Validation.Success
            expiredDate.isEmpty() -> Validation.Failure.Empty
            else -> Validation.Failure.Error(R.string.date_length_error)
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
        return when {
            password.length == 4 -> Validation.Success
            password.isEmpty() -> Validation.Failure.Empty
            else -> Validation.Failure.Error(R.string.password_length_error)
        }
    }

    fun addCard(onComplete: (() -> Unit)?) {
        when (val validation = _uiState.value.validateAllContents()) {
            is Validation.Failure -> {
                viewModelScope.launch {
                    _event.emit(NewCardEvent.ShowToast(validation.msgId))
                }
            }

            Validation.Success -> {
                paymentCardsRepository.addCard(_uiState.value.card)
                onComplete?.invoke()
            }
        }
    }
}
