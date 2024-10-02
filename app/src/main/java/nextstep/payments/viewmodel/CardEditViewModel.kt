package nextstep.payments.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import nextstep.payments.model.Card
import nextstep.payments.model.CardCompanyType
import nextstep.payments.repository.PaymentCardsRepository

class CardEditViewModel(private val repository: PaymentCardsRepository = PaymentCardsRepository) :
    ViewModel() {

    private val _cardNumber = MutableStateFlow("")
    val cardNumber: StateFlow<String> = _cardNumber.asStateFlow()

    private val _expiredDate = MutableStateFlow("")
    val expiredDate: StateFlow<String> = _expiredDate.asStateFlow()

    private val _ownerName = MutableStateFlow("")
    val ownerName: StateFlow<String> = _ownerName.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _cardEdited = MutableStateFlow(false)
    val cardEdited: StateFlow<Boolean> = _cardEdited.asStateFlow()

    private val _cardCompanyType = MutableStateFlow<CardCompanyType>(CardCompanyType.None)
    val cardCompanyType: StateFlow<CardCompanyType> = _cardCompanyType.asStateFlow()

    private var _cardId = 0
    private var _cardColor = 0L

    private var _cardCompany = ""
    val cardCompany: String
        get() = _cardCompany

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

    fun editCard(cardId: Int, card: Card) {
        repository.updateCard(cardId, card)
        _cardEdited.value = true
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
        _cardCompanyType.value = cardCompanyType
    }

    fun resetCardData(card: Card) {
        _cardId = card.id
        _cardNumber.value = card.cardNumber
        _expiredDate.value = card.expiredDate
        _ownerName.value = card.ownerName
        _password.value = card.password
        _cardColor = card.color
        _cardCompany = card.cardCompany
        updateCardCompany(card.cardCompany)
    }

    fun readCurrentCard(): Card {
        return Card(
            id = _cardId,
            cardNumber = _cardNumber.value,
            expiredDate = _expiredDate.value,
            ownerName = _ownerName.value,
            password = _password.value,
            color = _cardColor,
            cardCompany = _cardCompany
        )
    }
}

