package nextstep.payments.ui.register

import android.util.Log
import androidx.lifecycle.SavedStateHandle
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
import nextstep.payments.model.Brand
import nextstep.payments.model.Card
import nextstep.payments.model.OwnerNameValidResult
import nextstep.payments.ui.register.navigation.ARG_CARD_ID

class RegisterCardViewModel(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _uiState = MutableStateFlow(RegisterCardUiState.NONE)
    val uiState: StateFlow<RegisterCardUiState> = _uiState.asStateFlow()

    private val _effect = MutableSharedFlow<RegisterCardScreenEffect>()
    val effect = _effect.asSharedFlow()

    private val cardId: Long? = savedStateHandle.get<String>(ARG_CARD_ID)?.toLongOrNull()

    init {
        Log.d("RegisterCardViewModel", "cardId: $cardId")
        if (cardId != null) {
            PaymentCardsRepository
                .getCardById(cardId)
                ?.let { card ->
                    _uiState.update {
                        it.copy(
                            brand = card.brand,
                            cardNumber = card.cardNumber,
                            expiredDate = card.expiredDate,
                            ownerName = card.ownerName,
                            password = card.password,
                            mode = RegisterCardUiState.Mode.MODIFY,
                        )
                    }
                }
        }
    }

    fun dispatchEvent(event: RegisterCardScreenEvent) {
        when (event) {
            is RegisterCardScreenEvent.OnCardNumberChanged -> setCardNumber(event.cardNumber)
            is RegisterCardScreenEvent.OnExpiredDateChanged -> setExpiredDate(event.expiredDate)
            is RegisterCardScreenEvent.OnOwnerNameChanged -> setOwnerName(event.ownerName)
            is RegisterCardScreenEvent.OnPasswordChanged -> setPassword(event.password)
            is RegisterCardScreenEvent.OnRegisterCardClicked -> registerCard()
            is RegisterCardScreenEvent.OnBrandSelected -> setBrand(event.brand)
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
                RegisterCardScreenEffect.NavigateToCardListScreen(shouldFetchCards = true),
            )
        }
    }

    private fun setBrand(brand: Brand) {
        _uiState.update {
            it.copy(brand = brand)
        }
    }
}
