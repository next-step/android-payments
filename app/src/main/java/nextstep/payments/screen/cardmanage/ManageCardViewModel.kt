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
import nextstep.payments.screen.model.toUiModel

class ManageCardViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    val cardArgType = savedStateHandle.getStateFlow<CardArgType>(
        CardArgType.MANAGE_CARD_TYPE_ARG,
        CardArgType.AddCardArg
    )

    private val _cardNumber = MutableStateFlow(cardArgType.value.creditCardToEdit?.cardNumber ?: "")
    val cardNumber: StateFlow<String> = _cardNumber.asStateFlow()

    private val _expiredDate =
        MutableStateFlow(cardArgType.value.creditCardToEdit?.expiredDate ?: "")
    val expiredDate: StateFlow<String> = _expiredDate.asStateFlow()

    private val _ownerName = MutableStateFlow(cardArgType.value.creditCardToEdit?.ownerName ?: "")
    val ownerName: StateFlow<String> = _ownerName.asStateFlow()

    private val _password = MutableStateFlow(cardArgType.value.creditCardToEdit?.password ?: "")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _bankType = MutableStateFlow(cardArgType.value.creditCardToEdit?.bankType?.toUiModel())
    val bankType: StateFlow<BankTypeUiModel?> = _bankType.asStateFlow()

    val isAddCardEnabled: StateFlow<Boolean> =
        combine(
            cardNumber,
            expiredDate,
            ownerName,
            password,
            bankType
        ) { cardNumber, expiredDate, ownerName, password, bankType ->
            cardArgType.value.creditCardToEdit?.let { creditCard ->
                if(isNotCardChanged(creditCard, cardNumber, expiredDate, ownerName, password, bankType))
                    return@combine false
            }
            isValidInputs(cardNumber, expiredDate, password, bankType)
        }.stateIn(
            scope = viewModelScope,
            initialValue = false,
            started = SharingStarted.WhileSubscribed(500)
        )

    private fun isNotCardChanged(
        creditCard: CreditCard,
        cardNumber: String,
        expiredDate: String,
        ownerName: String,
        password: String,
        bankType: BankTypeUiModel?
    ) = creditCard.cardNumber == cardNumber &&
            creditCard.expiredDate == expiredDate &&
            creditCard.ownerName == ownerName &&
            creditCard.password == password &&
            creditCard.bankType.toUiModel() == bankType

    private fun isValidInputs(
        cardNumber: String,
        expiredDate: String,
        password: String,
        bankType: BankTypeUiModel?
    ) = cardNumber.length == 16 && expiredDate.length == 4 && password.length == 4 && bankType != null


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
