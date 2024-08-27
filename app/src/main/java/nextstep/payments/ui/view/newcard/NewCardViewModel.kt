package nextstep.payments.ui.view.newcard

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import nextstep.payments.data.PaymentCardRepository
import nextstep.payments.enums.CardCompanyCategory
import nextstep.payments.model.PaymentCardModel

class NewCardViewModel(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val paymentCard = savedStateHandle.get<PaymentCardModel>(NewCardActivity.EXTRA_PAYMENT_CARD)

    val isEdit = paymentCard != null

    private val _cardCompanyCategory = MutableStateFlow(paymentCard?.cardCompanyCategory)
    val cardCompanyCategory: StateFlow<CardCompanyCategory?> = _cardCompanyCategory.asStateFlow()

    private val _cardNumber = MutableStateFlow(paymentCard?.cardNumber.orEmpty())
    val cardNumber: StateFlow<String> = _cardNumber.asStateFlow()

    private val _expiredDate = MutableStateFlow(paymentCard?.expiredDate.orEmpty())
    val expiredDate: StateFlow<String> = _expiredDate.asStateFlow()

    private val _ownerName = MutableStateFlow(paymentCard?.ownerName.orEmpty())
    val ownerName: StateFlow<String> = _ownerName.asStateFlow()

    private val _password = MutableStateFlow(paymentCard?.password.orEmpty())
    val password: StateFlow<String> = _password.asStateFlow()

    val canSave = combine(
        cardCompanyCategory,
        cardNumber,
        expiredDate,
        ownerName,
        password,
    ) {
            cardCompanyCategory,
            cardNumber,
            expiredDate,
            ownerName,
            password,
        ->
        paymentCard == null ||
                paymentCard.cardCompanyCategory != cardCompanyCategory ||
                paymentCard.cardNumber != cardNumber ||
                paymentCard.expiredDate != expiredDate ||
                paymentCard.ownerName != ownerName ||
                paymentCard.password != password
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = false
    )

    private val _finishEvent = MutableSharedFlow<Unit>(replay = 0, extraBufferCapacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val finishEvent = _finishEvent.asSharedFlow()

    fun setCardCompany(cardCompanyCategory: CardCompanyCategory) {
        _cardCompanyCategory.value = cardCompanyCategory
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

    fun saveCard() {
        if (!canSave.value) return
        val cardCompanyCategory = _cardCompanyCategory.value ?: return run { _finishEvent.tryEmit(Unit) }
        val cardNumber = _cardNumber.value
        val expiredDate = _expiredDate.value
        val ownerName = _ownerName.value
        val password = _password.value
        if (cardNumber.isNotBlank() &&
            expiredDate.isNotBlank() &&
            password.isNotBlank()
        ) {
            if (paymentCard != null) {
                PaymentCardRepository.addCard(
                    paymentCard.copy(
                        cardCompanyCategory = cardCompanyCategory,
                        cardNumber = cardNumber,
                        expiredDate = expiredDate,
                        ownerName = ownerName,
                        password = password
                    )
                )
            } else {
                PaymentCardRepository.addCard(
                    PaymentCardModel(
                        cardCompanyCategory = cardCompanyCategory,
                        cardNumber = cardNumber,
                        expiredDate = expiredDate,
                        ownerName = ownerName,
                        password = password
                    )
                )
            }
            _finishEvent.tryEmit(Unit)
        }
    }
}
