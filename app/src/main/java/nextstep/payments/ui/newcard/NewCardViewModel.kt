package nextstep.payments.ui.newcard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import nextstep.payments.model.CreditCard
import nextstep.payments.model.IssuingBank
import nextstep.payments.repository.PaymentCardsRepository
import nextstep.payments.ui.form.PaymentCardFormState

class NewCardViewModel(private val repository: PaymentCardsRepository = PaymentCardsRepository) :
    ViewModel() {

    private val _uiState = MutableStateFlow(
        PaymentCardFormState(
            onCardNumberChanged = ::setCardNumber,
            onExpiredDateChanged = ::setExpiredDate,
            onOwnerNameChanged = ::setOwnerName,
            onPasswordChanged = ::setPassword,
        )
    )
    val uiState: StateFlow<PaymentCardFormState> = _uiState.asStateFlow()

    private val _effect = Channel<NewCardEffect>()
    val effect = _effect.receiveAsFlow()

    private fun setCardNumber(cardNumber: String) {
        if (cardNumber.length > MAX_CARD_NUMBER_LENGTH) return
        _uiState.value = _uiState.value.copy(cardNumber = cardNumber.filter { it.isDigit() })
    }

    private fun setExpiredDate(expiredDate: String) {
        if (expiredDate.length > MAX_EXPIRED_DATE_LENGTH) return
        _uiState.value = _uiState.value.copy(expiredDate = expiredDate.filter { it.isDigit() })
    }

    private fun setOwnerName(ownerName: String) {
        if (ownerName.length > MAX_OWNER_NAME_LENGTH) return
        _uiState.value = _uiState.value.copy(ownerName = ownerName.filter { it.isLetter() })
    }

    private fun setPassword(password: String) {
        if (password.length > MAX_PASSWORD_LENGTH) return
        _uiState.value = _uiState.value.copy(password = password.filter { it.isDigit() })
    }

    fun setIssuingBank(issuingBank: IssuingBank) {
        _uiState.value = _uiState.value.copy(issuingBank = issuingBank)
    }

    fun onSaveClick() = viewModelScope.launch {
        if (repository.cards.any { it.cardNumber == _uiState.value.cardNumber }) {
            _effect.send(NewCardEffect.ShowError("이미 등록된 카드 번호입니다."))
            return@launch
        }
        val issuingBank = _uiState.value.issuingBank
        if (issuingBank == null) {
            _effect.send(NewCardEffect.ShowError("카드사를 선택해주세요."))
            return@launch
        }
        saveCard(issuingBank)
    }

    private fun saveCard(issuingBank: IssuingBank) = viewModelScope.launch {
        val uiState = _uiState.value
        repository.addCard(
            CreditCard(
                id = -1L,
                cardNumber = uiState.cardNumber,
                expiredDate = uiState.expiredDate,
                ownerName = uiState.ownerName,
                password = uiState.password,
                issuingBank = issuingBank
            )
        )
        _effect.send(NewCardEffect.CardAdded)
    }

    companion object {
        const val MAX_CARD_NUMBER_LENGTH = 16
        const val MAX_EXPIRED_DATE_LENGTH = 4
        const val MAX_OWNER_NAME_LENGTH = 10
        const val MAX_PASSWORD_LENGTH = 4
    }
}
