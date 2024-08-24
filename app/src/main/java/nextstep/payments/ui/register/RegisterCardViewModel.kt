package nextstep.payments.ui.register

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
        if (cardNumber.length > CARD_NUMBER_MAX_LENGTH) return
        _uiState.update {
            val state = it.copy(cardNumber = cardNumber)
            val enabled = checkRegisterEnabled(state)
            state.copy(registerEnabled = enabled)
        }
    }

    private fun setExpiredDate(expiredDate: String) {
        if (expiredDate.length > EXPIRED_DATE_MAX_LENGTH) return
        _uiState.update {
            val state = it.copy(expiredDate = expiredDate)
            val enabled = checkRegisterEnabled(state)
            state.copy(registerEnabled = enabled)
        }
    }

    private fun setOwnerName(ownerName: String) {
        val result = validateOwnerName(ownerName)
        _uiState.update {
            val state =
                it.copy(
                    ownerName = ownerName,
                    ownerNameValidResult = result,
                )
            val enabled = checkRegisterEnabled(state)
            state.copy(registerEnabled = enabled)
        }
    }

    private fun validateOwnerName(ownerName: String): OwnerNameValidResult =
        when {
            ownerName.length > OWNER_NAME_MAX_LENGTH -> OwnerNameValidResult.ERROR_OWNER_NAME_LENGTH
            else -> OwnerNameValidResult.VALID
        }

    private fun setPassword(password: String) {
        if (password.length > PASSWORD_MAX_LENGTH) return
        _uiState.update {
            val state = it.copy(password = password)
            val enabled = checkRegisterEnabled(state)
            state.copy(registerEnabled = enabled)
        }
    }

    private fun setBrand(brand: Brand) {
        _uiState.update {
            val state = it.copy(brand = brand)
            val enabled = checkRegisterEnabled(state)
            state.copy(registerEnabled = enabled)
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

    private fun checkRegisterEnabled(uiState: RegisterCardUiState): Boolean =
        if (uiState.mode.isRegister()) {
            isRegisterEnabled(uiState)
        } else {
            isModified(uiState) && isRegisterEnabled(uiState)
        }

    private fun isRegisterEnabled(uiState: RegisterCardUiState): Boolean =
        uiState.cardNumber.length == CARD_NUMBER_MAX_LENGTH &&
            uiState.expiredDate.length == EXPIRED_DATE_MAX_LENGTH &&
            uiState.password.length == PASSWORD_MAX_LENGTH &&
            uiState.brand != Brand.NONE &&
            uiState.ownerNameValidResult != OwnerNameValidResult.ERROR_OWNER_NAME_LENGTH

    private fun isModified(uiState: RegisterCardUiState): Boolean {
        val id = cardId ?: return false
        val originalCard = PaymentCardsRepository.getCardById(id) ?: return false
        return isCardModified(originalCard, uiState)
    }

    private fun isCardModified(
        originalCard: Card,
        uiState: RegisterCardUiState,
    ): Boolean =
        originalCard.brand != uiState.brand ||
            originalCard.cardNumber != uiState.cardNumber ||
            originalCard.expiredDate != uiState.expiredDate ||
            originalCard.ownerName != uiState.ownerName ||
            originalCard.password != uiState.password

    companion object {
        private const val CARD_NUMBER_MAX_LENGTH = 16
        private const val EXPIRED_DATE_MAX_LENGTH = 4
        private const val PASSWORD_MAX_LENGTH = 4
        private const val OWNER_NAME_MAX_LENGTH = 30
    }
}
