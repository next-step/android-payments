package nextstep.payments.ui.screen.newcard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import nextstep.payments.data.CardData
import nextstep.payments.data.PaymentCardsRepository
import nextstep.payments.ui.screen.newcard.model.BankTypeModel
import nextstep.payments.ui.screen.newcard.model.toData

class NewCardViewModel : ViewModel() {

    private val _cardNumber = MutableStateFlow("")
    val cardNumber: StateFlow<String> = _cardNumber.asStateFlow()

    private val _expiredDate = MutableStateFlow("")
    val expiredDate: StateFlow<String> = _expiredDate.asStateFlow()

    private val _ownerName = MutableStateFlow("")
    val ownerName: StateFlow<String> = _ownerName.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _cardAdded = MutableStateFlow<Boolean>(false)
    val cardAdded = _cardAdded.asStateFlow()

    private val _selectedCard = MutableStateFlow<BankTypeModel>(BankTypeModel.NOT_SELECTED)
    val selectedCard = _selectedCard.asStateFlow()

    private val _snackbarMessages = MutableSharedFlow<String>()
    val snackbarMessages = _snackbarMessages.asSharedFlow()

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

    fun setSelectedCard(bankTypeModel: BankTypeModel) {
        _selectedCard.value = bankTypeModel
    }

    fun addCard() {
        if (selectedCard.value != BankTypeModel.NOT_SELECTED) {
            PaymentCardsRepository.addCard(
                card = CardData(
                    cardNumber = _cardNumber.value,
                    cardOwnerName = _ownerName.value,
                    cardExpiredDate = _expiredDate.value,
                    cardPassword = _password.value,
                    bankType = _selectedCard.value.toData()
                )
            )
            _cardAdded.value = true
        } else {
            viewModelScope.launch {
                _snackbarMessages.emit("카드를 클릭해서 카드 회사를 선택해주세요.")
            }
        }
    }
}
