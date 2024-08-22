package nextstep.payments.ui.newcard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nextstep.payments.data.PaymentCardsRepository
import nextstep.payments.model.Card
import nextstep.payments.model.OwnerNameValidResult

class NewCardViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(NewCardUiState.NONE)
    val uiState: StateFlow<NewCardUiState> = _uiState.asStateFlow()

    private val _effect = MutableSharedFlow<NewCardScreenEffect>()
    val effect = _effect.asSharedFlow()

    fun dispatchEvent(event: NewCardScreenEvent) {
        when (event) {
            is NewCardScreenEvent.OnCardNumberChanged -> setCardNumber(event.cardNumber)
            is NewCardScreenEvent.OnExpiredDateChanged -> setExpiredDate(event.expiredDate)
            is NewCardScreenEvent.OnOwnerNameChanged -> setOwnerName(event.ownerName)
            is NewCardScreenEvent.OnPasswordChanged -> setPassword(event.password)
            is NewCardScreenEvent.OnRegisterCardClicked -> registerCard()
        }
    }

    private fun setCardNumber(cardNumber: String) {
        _uiState.update {
            it.copy(cardNumber = cardNumber)
        }
    }

    private fun setExpiredDate(expiredDate: String) {
        _uiState.update {
            it.copy(expiredDate = expiredDate)
        }
    }

    private fun setOwnerName(ownerName: String) {
        val result = validateOwnerName(ownerName)
        _uiState.update {
            it.copy(
                ownerName = ownerName,
                ownerNameValidResult = result,
            )
        }
    }

    private fun validateOwnerName(ownerName: String): OwnerNameValidResult =
        when {
            ownerName.length > 30 -> OwnerNameValidResult.ERROR_OWNER_NAME_LENGTH
            else -> OwnerNameValidResult.VALID
        }

    private fun setPassword(password: String) {
        _uiState.update {
            it.copy(password = password)
        }
    }

    private fun registerCard() {
        viewModelScope.launch {
            val card =
                Card(
                    brand = _uiState.value.brand,
                    cardNumber = _uiState.value.cardNumber,
                    expiredDate = _uiState.value.expiredDate,
                    ownerName = _uiState.value.ownerName,
                    password = _uiState.value.password,
                )
            PaymentCardsRepository.addCard(card)
            _effect.emit(
                NewCardScreenEffect.NavigateToCardListScreen(shouldFetchCards = true),
            )
        }
    }
}
