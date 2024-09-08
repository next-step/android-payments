package nextstep.payments.ui.newcard

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
import nextstep.payments.model.Card
import nextstep.payments.model.CardCompany

class NewCardViewModel (
    savedStateHandle: SavedStateHandle,
): ViewModel() {
    private val cardModification: Card? = savedStateHandle[NewCardActivity.MODIFY_CARD]

    private val _isModify = MutableStateFlow(cardModification != null)
    val isModify: StateFlow<Boolean> = _isModify.asStateFlow()

    private val _cardAdded = MutableStateFlow<Boolean>(false)
    val cardAdded: StateFlow<Boolean> = _cardAdded.asStateFlow()

    private val _cardNumber = MutableStateFlow(cardModification?.cardNumber?: "")
    val cardNumber: StateFlow<String> = _cardNumber.asStateFlow()

    private val _expiredDate = MutableStateFlow(cardModification?.expiredDate ?: "")
    val expiredDate: StateFlow<String> = _expiredDate.asStateFlow()

    private val _ownerName = MutableStateFlow(cardModification?.ownerName ?: "")
    val ownerName: StateFlow<String> = _ownerName.asStateFlow()

    private val _password = MutableStateFlow(cardModification?.password ?: "")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _selectedCard = MutableStateFlow<CardCompany?>(cardModification?.cardCompany)
    val selectedCard: StateFlow<CardCompany?> = _selectedCard.asStateFlow()

    private val _cardCompanies = MutableStateFlow<List<CardCompany>>(CardCompany.entries)
    val cardCompanies = _cardCompanies.asStateFlow()

    val canSave = combine(
        cardNumber,
        expiredDate,
        ownerName,
        password,
        selectedCard
    ) { cardNumber, expiredDate, ownerName, password, company ->
        val isNotEmpty = cardNumber.isNotBlank() && expiredDate.isNotBlank() &&
                ownerName.isNotBlank() && password.isNotBlank()
        val isChanged = cardModification == null ||
                cardNumber != cardModification.cardNumber ||
                expiredDate != cardModification.expiredDate ||
                ownerName != cardModification.ownerName ||
                password != cardModification.password ||
                company != cardModification.cardCompany
        isNotEmpty && isChanged
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = false
    )
    

    fun setCardCompany(cardCompany: CardCompany) {
        _selectedCard.value = cardCompany
    }

    fun saveCard(card: Card) {
        if(cardModification != null){
            val modifyCard = card.copy(id = cardModification.id)
            PaymentCardsRepository.modifyCard(modifyCard)
        } else
            PaymentCardsRepository.addCard(card)
        _cardAdded.value = true
    }

    fun setCardNumber(cardNumber: String) {
        if (cardNumber.length <= 16) {
            _cardNumber.value = cardNumber
        }
    }

    fun setExpiredDate(expiredDate: String) {
        if (expiredDate.length <= 4) {
            _expiredDate.value = expiredDate
        }
    }

    fun setOwnerName(ownerName: String) {
        if (ownerName.length <= 30) {
            _ownerName.value = ownerName
        }
    }

    fun setPassword(password: String) {
        if (password.length <= 4) {
            _password.value = password
        }
    }
}