package nextstep.payments.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import nextstep.payments.data.model.CardModel
import nextstep.payments.data.repository.PaymentCardsRepository

class CardAddViewModel(
    private val repository: PaymentCardsRepository = PaymentCardsRepository
) : ViewModel() {

    private val _cardModel = MutableStateFlow<CardModel>(CardModel())
    val cardModel: StateFlow<CardModel> = _cardModel.asStateFlow()

    private val _cardAdded = MutableStateFlow<Boolean>(false)
    val cardAdded: StateFlow<Boolean> = _cardAdded.asStateFlow()

    fun setCardNumber(cardNumber: String) {
        _cardModel.value = _cardModel.value.copy(
            number = cardNumber
        )
    }

    fun setExpiredDate(expiredDate: String) {
        _cardModel.value = _cardModel.value.copy(
            expiredDate = expiredDate
        )
    }

    fun setOwnerName(ownerName: String) {
        _cardModel.value = _cardModel.value.copy(
            ownerName = ownerName
        )
    }

    fun setPassword(password: String) {
        _cardModel.value = _cardModel.value.copy(
            password = password
        )
    }

    fun addCard() {
        repository.addCard(_cardModel.value)
        _cardAdded.value = true
    }
}
