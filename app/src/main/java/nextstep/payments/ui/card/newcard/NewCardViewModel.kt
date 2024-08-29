package nextstep.payments.ui.card.newcard

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import nextstep.payments.R
import nextstep.payments.data.PaymentCardRepository
import nextstep.payments.model.BankType
import nextstep.payments.model.Card
import nextstep.payments.ui.card.newcard.model.UiNewCard

class NewCardViewModel(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    val editCard: Card? = savedStateHandle[NewCardActivity.EXTRA_CARD]

    private val _cardAdded = MutableStateFlow(false)
    val cardAdded: StateFlow<Boolean> = _cardAdded.asStateFlow()

    private val _uiState = MutableStateFlow(UiNewCard.init(editCard))
    val uiState: StateFlow<UiNewCard> = _uiState.asStateFlow()

    val saveEnabled: StateFlow<Boolean> = uiState
        .map {
            editCard?.let {
                it.bankType != uiState.value.bankType || it.cardNumber != uiState.value.cardNumber || it.expiredDate != uiState.value.expiredDate || it.ownerName != uiState.value.ownerName
            } ?: true
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = false,
        )

    private val _sideEffect: MutableSharedFlow<NewCardSideEffect> = MutableSharedFlow()
    val sideEffect = _sideEffect.asSharedFlow()

    fun setBankType(bankType: BankType) {
        _uiState.value = uiState.value.copy(bankType = bankType)
    }

    fun setCardNumber(cardNumber: String) {
        _uiState.value = uiState.value.copy(cardNumber = cardNumber)
    }

    fun setExpiredDate(expiredDate: String) {
        _uiState.value = uiState.value.copy(expiredDate = expiredDate)
    }

    fun setOwnerName(ownerName: String) {
        _uiState.value = uiState.value.copy(ownerName = ownerName)
    }

    fun setPassword(password: String) {
        _uiState.value = uiState.value.copy(password = password)
    }

    fun addCard() {
        val card = Card(
            id = editCard?.id ?: 0,
            bankType = uiState.value.bankType ?: run {
                viewModelScope.launch { _sideEffect.emit(NewCardSideEffect.ShowToast(R.string.select_bank_type)) }
                return
            },
            cardNumber = uiState.value.cardNumber,
            expiredDate = uiState.value.expiredDate,
            ownerName = uiState.value.ownerName,
            password = uiState.value.password,
        )
        PaymentCardRepository.addCard(card)
        _cardAdded.value = true
    }

}
