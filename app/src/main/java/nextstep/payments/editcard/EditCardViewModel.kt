package nextstep.payments.editcard

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import nextstep.payments.PaymentCardsRepository
import nextstep.payments.R
import nextstep.payments.common.model.CardCompany
import nextstep.payments.common.model.Validation
import nextstep.payments.editcard.model.EditCardEvent
import nextstep.payments.editcard.model.EditCardUiState

class EditCardViewModel(
    private val paymentCardsRepository: PaymentCardsRepository = PaymentCardsRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(EditCardUiState())
    val uiState: StateFlow<EditCardUiState> = _uiState.asStateFlow()

    private val _event = MutableSharedFlow<EditCardEvent>(
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val event = _event.asSharedFlow()

    fun fetchCard(id: Int) {
        val card = paymentCardsRepository.cards.find { it.id == id }
        if (card == null) {
            _event.tryEmit(EditCardEvent.Finish(R.string.notice_invalid_id))
            return
        }
        _uiState.update {
            EditCardUiState(card = card)
        }
    }

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

    fun editCard() {
        when (val validation = _uiState.value.validateAllContents()) {
            is Validation.Failure -> {
                _event.tryEmit(EditCardEvent.ShowToast(validation.msgId))
            }

            Validation.Success -> {
                paymentCardsRepository.editCard(_uiState.value.card)
                _event.tryEmit(EditCardEvent.Finish(null))
            }

            Validation.Init -> {
                _event.tryEmit(EditCardEvent.ShowToast(R.string.value_nothing_changed))
            }
        }
    }
}
