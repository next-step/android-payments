package nextstep.payments.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import nextstep.payments.model.Card
import nextstep.payments.model.CardCompanyType
import nextstep.payments.model.CardEditUiState
import nextstep.payments.repository.PaymentCardsRepository

class CardEditViewModel(private val repository: PaymentCardsRepository = PaymentCardsRepository) :
    ViewModel() {

    private val _uiState = MutableStateFlow(CardEditUiState())
    val uiState: StateFlow<CardEditUiState> = _uiState.asStateFlow()

    private var _cardId = 0
    private var _cardColor = 0L
    private var _cardCompany = ""

    init {
        _uiState.update { uiState ->
            uiState.copy(
                isCardEdited = uiState.cardNumber != uiState.initialCardNumber ||
                        uiState.expiredDate != uiState.initialExpiredDate ||
                        uiState.ownerName != uiState.initialOwnerName ||
                        uiState.password != uiState.initialPassword
            )
        }
    }

    fun setCardNumber(cardNumber: String) {
        if (_uiState.value.cardNumber != cardNumber) {
            _uiState.value = _uiState.value.copy(
                initialCardNumber = _uiState.value.cardNumber,
                cardNumber = cardNumber,
                isCardEdited = true,
                currentCard = readCurrentCard().copy(cardNumber = cardNumber)
            )
        }
    }

    fun setExpiredDate(expiredDate: String) {
        if (_uiState.value.expiredDate != expiredDate) {
            _uiState.value = _uiState.value.copy(
                initialExpiredDate = _uiState.value.expiredDate,
                expiredDate = expiredDate,
                isCardEdited = true,
                currentCard = readCurrentCard().copy(expiredDate = expiredDate)
            )
        }
    }

    fun setOwnerName(ownerName: String) {
        if (_uiState.value.ownerName != ownerName) {
            _uiState.value = _uiState.value.copy(
                initialOwnerName = _uiState.value.ownerName,
                ownerName = ownerName,
                isCardEdited = true,
                currentCard = readCurrentCard().copy(ownerName = ownerName)
            )
        }
    }

    fun setPassword(password: String) {
        if (_uiState.value.password != password) {
            _uiState.value = _uiState.value.copy(
                initialPassword = _uiState.value.password,
                password = password,
                isCardEdited = true,
                currentCard = readCurrentCard().copy(password = password)
            )
        }
    }

    fun editCard(cardId: Int) {
        val card = Card(
            id = cardId,
            cardNumber = _uiState.value.cardNumber,
            expiredDate = _uiState.value.expiredDate,
            ownerName = _uiState.value.ownerName,
            password = _uiState.value.password,
            color = _cardColor,
            cardCompany = _cardCompany
        )
        repository.updateCard(cardId, card)
        _uiState.value = _uiState.value.copy(cardEdited = true)
    }

    private fun updateCardCompany(cardCompanyName: String) {
        val cardCompanyType = when (cardCompanyName) {
            "BC카드" -> CardCompanyType.Bc(cardCompanyName)
            "신한카드" -> CardCompanyType.Shinhan(cardCompanyName)
            "카카오뱅크" -> CardCompanyType.Kakaobank(cardCompanyName)
            "현대카드" -> CardCompanyType.Hyundai(cardCompanyName)
            "우리카드" -> CardCompanyType.Woori(cardCompanyName)
            "롯데카드" -> CardCompanyType.Lotte(cardCompanyName)
            "하나카드" -> CardCompanyType.Hana(cardCompanyName)
            "국민카드" -> CardCompanyType.Kb(cardCompanyName)
            else -> CardCompanyType.None
        }
        _uiState.value = _uiState.value.copy(cardCompanyType = cardCompanyType)
    }

    fun resetCardData(card: Card) {
        _cardId = card.id
        _cardColor = card.color
        _cardCompany = card.cardCompany
        _uiState.value = CardEditUiState(
            cardNumber = card.cardNumber,
            expiredDate = card.expiredDate,
            ownerName = card.ownerName,
            password = card.password,
            cardCompanyType = _uiState.value.cardCompanyType,
            initialCardNumber = card.cardNumber,
            initialExpiredDate = card.expiredDate,
            initialOwnerName = card.ownerName,
            initialPassword = card.password,
            currentCard = card
        )
        updateCardCompany(card.cardCompany)
    }

    private fun readCurrentCard(): Card {
        return Card(
            id = _cardId,
            cardNumber = _uiState.value.cardNumber,
            expiredDate = _uiState.value.expiredDate,
            ownerName = _uiState.value.ownerName,
            password = _uiState.value.password,
            color = _cardColor,
            cardCompany = _cardCompany
        )
    }
}
