package nextstep.payments.screen.cardmanage

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import nextstep.payments.data.PaymentCardsRepository
import nextstep.payments.data.model.CreditCard
import nextstep.payments.screen.model.BankTypeUiModel
import nextstep.payments.screen.model.arg.CardArgType
import nextstep.payments.screen.model.toEntity

class ManageCardViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val cardArgType = savedStateHandle.getStateFlow<CardArgType>(
        CardArgType.MANAGE_CARD_TYPE_ARG,
        CardArgType.AddCardArg
    )

    private val _cardNumber = MutableStateFlow("")
    val cardNumber: StateFlow<String> = _cardNumber.asStateFlow()

    private val _expiredDate = MutableStateFlow("")
    val expiredDate: StateFlow<String> = _expiredDate.asStateFlow()

    private val _ownerName = MutableStateFlow("")
    val ownerName: StateFlow<String> = _ownerName.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _bankType = MutableStateFlow<BankTypeUiModel?>(null)
    val bankType: StateFlow<BankTypeUiModel?> = _bankType.asStateFlow()

    val isAddCardEnabled: StateFlow<Boolean> =
        combine(
            cardNumber,
            expiredDate,
            password,
            bankType
        ) { cardNumber, expiredDate, password, bankType ->
            cardNumber.length == 16 && expiredDate.length == 4 && password.length == 4 && bankType != null
        }.stateIn(
            scope = viewModelScope,
            initialValue = false,
            started = SharingStarted.WhileSubscribed(500)
        )

    private val _cardAdded = MutableStateFlow(ManageCardEvent.Pending)
    val cardAdded: StateFlow<ManageCardEvent> = _cardAdded.asStateFlow()

    fun setCardNumber(cardNumber: String) {
        if (cardNumber.length > 16) return
        _cardNumber.value = cardNumber
    }

    fun setExpiredDate(expiredDate: String) {
        if (expiredDate.length > 4) return
        _expiredDate.value = expiredDate
    }

    fun setOwnerName(ownerName: String) {
        _ownerName.value = ownerName
    }

    fun setPassword(password: String) {
        if (password.length > 4) return
        _password.value = password
    }

    fun setBankType(bankTypeUiModel: BankTypeUiModel) {
        _bankType.value = bankTypeUiModel
    }

    fun addCard() {
        val selectedBankType = bankType.value?.toEntity() ?: return

        PaymentCardsRepository.addCard(
            CreditCard(
                cardNumber = cardNumber.value,
                expiredDate = expiredDate.value,
                ownerName = ownerName.value,
                password = password.value,
                bankType = selectedBankType
            )
        )
        _cardAdded.value = ManageCardEvent.Success
    }

    fun cancelToAddCard() {
        _cardAdded.value = ManageCardEvent.Cancel
    }
}
