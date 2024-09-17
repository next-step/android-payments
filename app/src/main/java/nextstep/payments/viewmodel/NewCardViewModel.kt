package nextstep.payments.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import nextstep.payments.model.Card
import nextstep.payments.model.CardCompany
import nextstep.payments.model.CardCompanyType
import nextstep.payments.repository.CardCompaniesRepository
import nextstep.payments.repository.PaymentCardsRepository

class NewCardViewModel(private val repository: PaymentCardsRepository = PaymentCardsRepository) :
    ViewModel() {

    private val _cardNumber = MutableStateFlow("")
    val cardNumber: StateFlow<String> = _cardNumber.asStateFlow()

    private val _expiredDate = MutableStateFlow("")
    val expiredDate: StateFlow<String> = _expiredDate.asStateFlow()

    private val _ownerName = MutableStateFlow("")
    val ownerName: StateFlow<String> = _ownerName.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _cardAdded = MutableStateFlow<Boolean>(false)
    val cardAdded: StateFlow<Boolean> = _cardAdded.asStateFlow()

    private val _cardCompanyType = MutableStateFlow<CardCompanyType>(CardCompanyType.None)
    val cardCompanyType: StateFlow<CardCompanyType> = _cardCompanyType.asStateFlow()


    val cardCompanies: List<CardCompany> = CardCompaniesRepository.data

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

    fun addCard(card: Card) {
        repository.addCard(card)
        _cardAdded.value = true
    }

    fun updateCardCompany(cardCompany: CardCompany) {
        val cardCompanyType = when (cardCompany.name) {
            "BC카드" -> CardCompanyType.Bc(cardCompany.name)
            "신한카드" -> CardCompanyType.Shinhan(cardCompany.name)
            "카카오뱅크" -> CardCompanyType.Kakaobank(cardCompany.name)
            "현대카드" -> CardCompanyType.Hyundai(cardCompany.name)
            "우리카드" -> CardCompanyType.Woori(cardCompany.name)
            "롯데카드" -> CardCompanyType.Lotte(cardCompany.name)
            "하나카드" -> CardCompanyType.Hana(cardCompany.name)
            "국민카드" -> CardCompanyType.Kb(cardCompany.name)
            else -> CardCompanyType.None
        }
        _cardCompanyType.value = cardCompanyType
    }
}

