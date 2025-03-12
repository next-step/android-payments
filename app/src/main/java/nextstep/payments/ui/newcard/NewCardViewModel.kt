package nextstep.payments.ui.newcard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import nextstep.payments.model.CardFormFormatter
import nextstep.payments.model.CreditCard
import nextstep.payments.model.IssuingBank
import nextstep.payments.repository.PaymentCardsRepository
import nextstep.payments.ui.form.PaymentCardFormState

class NewCardViewModel(
    private val repository: PaymentCardsRepository = PaymentCardsRepository
) : ViewModel() {

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
        val formattedCardNumber = CardFormFormatter.formatCardNumber(cardNumber)
        _uiState.value = _uiState.value.copy(cardNumber = formattedCardNumber)
    }

    private fun setExpiredDate(expiredDate: String) {
        val formattedExpiredDate = CardFormFormatter.formatExpiredDate(expiredDate)
        _uiState.value = _uiState.value.copy(expiredDate = formattedExpiredDate)
    }

    private fun setOwnerName(ownerName: String) {
        val formattedOwnerName = CardFormFormatter.formatOwnerName(ownerName)
        _uiState.value = _uiState.value.copy(ownerName = formattedOwnerName)
    }

    private fun setPassword(password: String) {
        val formattedPassword = CardFormFormatter.formatPassword(password)
        _uiState.value = _uiState.value.copy(password = formattedPassword)
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
}
