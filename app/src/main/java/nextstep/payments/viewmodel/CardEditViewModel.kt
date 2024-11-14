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

    private val _uiState = MutableStateFlow(
        CardEditUiState(
            cardId = 0,
            cardColor = 0L,
            cardCompany = "",
            cardNumber = "",
            expiredDate = "",
            ownerName = "",
            password = "",
            cardEdited = false,
            cardCompanyType = CardCompanyType.None,
            initialCardNumber = "",
            initialExpiredDate = "",
            initialOwnerName = "",
            initialPassword = "",
            currentCard = Card(
                id = 0,
                cardNumber = "",
                expiredDate = "",
                ownerName = "",
                password = "",
                color = 0L,
                cardCompany = ""
            )
        )
    )
    val uiState: StateFlow<CardEditUiState> = _uiState.asStateFlow()

    fun setCardNumber(cardNumber: String) {
        if (_uiState.value.cardNumber != cardNumber) {
            _uiState.value = _uiState.value.copy(
                initialCardNumber = _uiState.value.cardNumber,
                cardNumber = cardNumber,
                currentCard = readCurrentCard().copy(cardNumber = cardNumber)
            )
        }
    }

    fun setExpiredDate(expiredDate: String) {
        if (_uiState.value.expiredDate != expiredDate) {
            _uiState.update { currentState ->
                currentState.copy(
                    initialExpiredDate = currentState.expiredDate,
                    expiredDate = expiredDate,
                    currentCard = currentState.currentCard.copy(expiredDate = expiredDate)
                )
            }
        }
    }

    fun setOwnerName(ownerName: String) {
        if (_uiState.value.ownerName != ownerName) {
            _uiState.update { currentState ->
                currentState.copy(
                    initialOwnerName = currentState.ownerName,
                    ownerName = ownerName,
                    currentCard = readCurrentCard().copy(ownerName = ownerName)
                )
            }
        }
    }

    fun setPassword(password: String) {
        if (_uiState.value.password != password) {
            _uiState.update { currentState ->
                currentState.copy(
                    initialPassword = currentState.password,
                    password = password,
                    currentCard = readCurrentCard().copy(password = password)
                )
            }
        }
    }

    fun editCard(cardId: Int) {
        val card = Card(
            id = cardId,
            cardNumber = _uiState.value.cardNumber,
            expiredDate = _uiState.value.expiredDate,
            ownerName = _uiState.value.ownerName,
            password = _uiState.value.password,
            color = _uiState.value.cardColor,
            cardCompany = _uiState.value.cardCompany
        )
        repository.updateCard(cardId, card)
        _uiState.update { currentState ->
            currentState.copy(cardEdited = true)
        }
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
        _uiState.value = CardEditUiState(
            cardId = card.id,
            cardColor = card.color,
            cardCompany = card.cardCompany,
            cardNumber = card.cardNumber,
            expiredDate = card.expiredDate,
            ownerName = card.ownerName,
            password = card.password,
            cardEdited = false,
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
            id = _uiState.value.cardId,
            cardNumber = _uiState.value.cardNumber,
            expiredDate = _uiState.value.expiredDate,
            ownerName = _uiState.value.ownerName,
            password = _uiState.value.password,
            color = _uiState.value.cardColor,
            cardCompany = _uiState.value.cardCompany
        )
    }
}
