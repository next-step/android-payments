package nextstep.payments.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import nextstep.payments.data.model.Card
import nextstep.payments.data.repository.PaymentCardsRepository
import nextstep.payments.ui.CardCompanyType

sealed class SaveState {
    data object UpdateCard : SaveState()
    data object SaveNewCard : SaveState()
    data object ShowSnackbar : SaveState()
}

class NewCardViewModel(
    private val paymentRepsoitory: PaymentCardsRepository = PaymentCardsRepository,
) : ViewModel() {

    private val _cardNumber = MutableStateFlow("")
    val cardNumber: StateFlow<String> = _cardNumber.asStateFlow()

    private val _expiredDate = MutableStateFlow("")
    val expiredDate: StateFlow<String> = _expiredDate.asStateFlow()

    private val _ownerName = MutableStateFlow("")
    val ownerName: StateFlow<String> = _ownerName.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _selectedCardCompany = MutableStateFlow<CardCompanyType?>(null)
    val selectedCardCompany: StateFlow<CardCompanyType?> = _selectedCardCompany.asStateFlow()

    private val _isSaveEnabled = MutableStateFlow(false)
    val isSaveEnabled: StateFlow<Boolean> = _isSaveEnabled.asStateFlow()

    private val _saveState = MutableSharedFlow<SaveState>(replay = 1)
    val saveState: SharedFlow<SaveState> = _saveState

    private var modifyCard: Card? = null

    init {
        viewModelScope.launch {
            combine(cardNumber, expiredDate, password) { card, date, pass ->
                card.length == 16 && date.length == 4 && pass.length == 4
            }.collect { valid ->
                _isSaveEnabled.value = valid
            }
        }
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

    fun setSelectedBank(cardCompanyType: CardCompanyType) {
        _selectedCardCompany.value = cardCompanyType
    }

    fun fetchCardById(cardId: String) {
        modifyCard = paymentRepsoitory.getCardById(cardId)

        modifyCard?.let { card ->
            _cardNumber.value = card.cardNumber
            _expiredDate.value = card.expiredDate
            _ownerName.value = card.ownerName
            _password.value = card.password
            _selectedCardCompany.value = card.cardCompanyType
        }
    }

    fun onSaveClick(cardId: String?, isSaveEnabled: Boolean) {

        // 저장이 불가능한 상태
        if (!isSaveEnabled) {
            _saveState.tryEmit(SaveState.ShowSnackbar)
            return
        }

        // 카드 수정
        if (cardId != null) {
            updateCard(
                cardId = cardId,
                cardNumber = _cardNumber.value,
                expiredDate = _expiredDate.value,
                ownerName = _ownerName.value,
                password = _password.value,
                cardCompanyType = _selectedCardCompany.value
            )

            _saveState.tryEmit(SaveState.UpdateCard)
            return
        }

        // 새로운 카드 추가
        addCard(
            cardNumber = _cardNumber.value,
            expiredDate = _expiredDate.value,
            ownerName = _ownerName.value,
            password = _password.value,
            cardCompanyType = _selectedCardCompany.value
        )

        _saveState.tryEmit(SaveState.SaveNewCard)
    }

    private fun addCard(
        cardNumber: String,
        expiredDate: String,
        ownerName: String,
        password: String,
        cardCompanyType: CardCompanyType?,
    ) {
        if (cardCompanyType == null) {
            return
        }

        paymentRepsoitory.addCard(
            Card(
                cardNumber = cardNumber,
                expiredDate = expiredDate,
                ownerName = ownerName,
                password = password,
                cardCompanyType = cardCompanyType
            )
        )
    }

    private fun updateCard(
        cardId: String,
        cardNumber: String,
        expiredDate: String,
        ownerName: String,
        password: String,
        cardCompanyType: CardCompanyType?,
    ) {
        if (cardCompanyType == null) {
            return
        }

        val updatedCard = Card(
            cardId = cardId,
            cardNumber = cardNumber,
            expiredDate = expiredDate,
            ownerName = ownerName,
            password = password,
            cardCompanyType = cardCompanyType
        )

        if (modifyCard == updatedCard) {
            _saveState.tryEmit(SaveState.ShowSnackbar)
            return
        }

        paymentRepsoitory.updateCard(
            updatedCard = updatedCard
        )
    }
}
