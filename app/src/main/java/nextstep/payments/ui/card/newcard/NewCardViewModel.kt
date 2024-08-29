package nextstep.payments.ui.card.newcard

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import nextstep.payments.R
import nextstep.payments.data.PaymentCardRepository
import nextstep.payments.model.BankType
import nextstep.payments.model.Card

class NewCardViewModel(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    val editCard: Card? = savedStateHandle[NewCardActivity.EXTRA_CARD]

    private val _cardAdded = MutableStateFlow(false)
    val cardAdded: StateFlow<Boolean> = _cardAdded.asStateFlow()

    private val _bankType: MutableStateFlow<BankType?> = MutableStateFlow(editCard?.bankType)
    val bankType: StateFlow<BankType?> = _bankType.asStateFlow()

    private val _cardNumber = MutableStateFlow(editCard?.cardNumber ?: "")
    val cardNumber: StateFlow<String> = _cardNumber.asStateFlow()

    private val _expiredDate = MutableStateFlow(editCard?.expiredDate ?: "")
    val expiredDate: StateFlow<String> = _expiredDate.asStateFlow()

    private val _ownerName = MutableStateFlow(editCard?.ownerName ?: "")
    val ownerName: StateFlow<String> = _ownerName.asStateFlow()

    private val _password = MutableStateFlow(editCard?.password ?: "")
    val password: StateFlow<String> = _password.asStateFlow()

    val saveEnabled: StateFlow<Boolean> = combine(
        bankType,
        cardNumber,
        expiredDate,
        ownerName,
    ) { bankType, cardNumber, expiredDate, ownerName ->
        editCard?.let {
            it.bankType != bankType || it.cardNumber != cardNumber || it.expiredDate != expiredDate || it.ownerName != ownerName
        } ?: true
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = false,
    )

    private val _sideEffect: MutableSharedFlow<NewCardSideEffect> = MutableSharedFlow()
    val sideEffect = _sideEffect.asSharedFlow()

    fun setBankType(bankType: BankType) {
        _bankType.value = bankType
    }

    fun setCardNumber(cardNumber: String) {
        _cardNumber.value = cardNumber
    }

    fun setExpiredDate(expiredDate: String) {
        _expiredDate.value = expiredDate
    }

    fun setOwnerName(ownerName: String) {
        _ownerName.value = ownerName
    }

    fun setPassword(password: String) {
        _password.value = password
    }

    fun addCard() {
        val bankType = bankType.value ?: run {
            Log.e("[NewCardViewModel]", "bankType cannot be null")
            viewModelScope.launch { _sideEffect.emit(NewCardSideEffect.ShowToast(R.string.select_bank_type)) }
            return
        }

        val card = Card(
            id = editCard?.id ?: 0,
            bankType = bankType,
            cardNumber = cardNumber.value,
            expiredDate = expiredDate.value,
            ownerName = ownerName.value,
            password = password.value,
        )
        PaymentCardRepository.addCard(card)
        _cardAdded.value = true
    }

}
