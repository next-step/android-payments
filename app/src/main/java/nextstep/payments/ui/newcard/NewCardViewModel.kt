package nextstep.payments.ui.newcard

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import nextstep.payments.data.repository.PaymentCardsRepository
import nextstep.payments.ui.model.CreditCardType.RegisteredCard
import nextstep.payments.ui.newcard.model.CardCompany

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

    private val _cardAdded = MutableStateFlow(false)
    val cardAdded: StateFlow<Boolean> = _cardAdded.asStateFlow()

    private val _selectedCard = MutableStateFlow(CardCompany(0, "", 0))
    val selectedCard: StateFlow<CardCompany> = _selectedCard.asStateFlow()

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

    fun updateCardName(cardCompany: CardCompany) {
        _selectedCard.value = cardCompany
    }

    fun saveCard() {
        // TODO when 문으로 각각 분기하여 에러내보내기
        if (cardNumber.value.length == 1 && expiredDate.value.length == 4 && password.value.length == 4 && ownerName.value.isNotEmpty()) {
            repository.addCard(
                RegisteredCard(
                    number = "12341234123412314",
                    expiredDate = expiredDate.value,
                    ownerName = ownerName.value,
                    password = password.value,
                    cardCompany = selectedCard.value
                )
            )
            _cardAdded.value = true
        } else {
            Log.d("error", "saveCard error")
            //TODO TextField에 오류 표시
        }
    }
}
