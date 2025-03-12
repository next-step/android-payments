package nextstep.payments.ui.cardedit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import nextstep.payments.model.CreditCard
import nextstep.payments.model.IssuingBank
import nextstep.payments.repository.PaymentCardsRepository
import nextstep.payments.ui.form.PaymentCardFormState

class CardEditViewModel(
    private val cardId: Long,
    private val paymentCardsRepository: PaymentCardsRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow<CardEditUiState>(CardEditUiState.Loading)
    val uiState: StateFlow<CardEditUiState> get() = _uiState.asStateFlow()

    private val _effect = Channel<CardEditEffect>()
    val effect: Flow<CardEditEffect> get() = _effect.receiveAsFlow()

    init {
        fetchCreditCard()
    }

    fun onIntent(intent: CardEditIntent) {
        when (intent) {
            is CardEditIntent.OnSaveCardEdit -> saveCardEdit()
            is CardEditIntent.OnCardNumberChanged -> changeCardNumber(intent.cardNumber)
            is CardEditIntent.OnExpiredDateChanged -> changeExpireDate(intent.expiredDate)
            is CardEditIntent.OnOwnerNameChanged -> changeOwnerName(intent.ownerName)
            is CardEditIntent.OnPasswordChanged -> changePassword(intent.password)
            is CardEditIntent.OnIssuingBankChanged -> changeIssuingBank(intent.issuingBank)
        }
    }

    private fun fetchCreditCard() {
        viewModelScope.launch {
            val card = paymentCardsRepository.findCard(cardId = cardId)
            if (card == null) {
                _effect.send(CardEditEffect.ShowError("카드 정보를 찾을 수 없습니다."))
                return@launch
            }
            _uiState.value = CardEditUiState.Success(
                creditCard = card,
                formState = PaymentCardFormState(
                    cardNumber = card.cardNumber,
                    expiredDate = card.expiredDate,
                    ownerName = card.ownerName,
                    password = card.password,
                    issuingBank = card.issuingBank,
                    onCardNumberChanged = { onIntent(CardEditIntent.OnCardNumberChanged(it)) },
                    onExpiredDateChanged = { onIntent(CardEditIntent.OnExpiredDateChanged(it)) },
                    onOwnerNameChanged = { onIntent(CardEditIntent.OnOwnerNameChanged(it)) },
                    onPasswordChanged = { onIntent(CardEditIntent.OnPasswordChanged(it)) },
                ),
            )
        }
    }

    private fun changeCardNumber(cardNumber: String) = setStateOnSuccess {
        copy(formState = formState.copy(cardNumber = cardNumber))
    }

    private fun changeExpireDate(expiredDate: String) = setStateOnSuccess {
        copy(formState = formState.copy(expiredDate = expiredDate))
    }

    private fun changeOwnerName(ownerName: String) = setStateOnSuccess {
        copy(formState = formState.copy(ownerName = ownerName))
    }

    private fun changePassword(password: String) = setStateOnSuccess {
        copy(formState = formState.copy(password = password))
    }

    private fun changeIssuingBank(issuingBank: IssuingBank) = setStateOnSuccess {
        copy(formState = formState.copy(issuingBank = issuingBank))
    }

    private fun setStateOnSuccess(
        reducer: CardEditUiState.Success.() -> CardEditUiState.Success
    ) {
        val state = _uiState.value as? CardEditUiState.Success ?: return
        _uiState.value = reducer(state)
    }

    private fun saveCardEdit() {
        viewModelScope.launch {
            val success = _uiState.value as? CardEditUiState.Success ?: return@launch
            val card = success.creditCard

            if (isEqualCard(card, success.formState)) {
                _effect.send(CardEditEffect.ShowError("변경된 내용이 없습니다."))
                return@launch
            }

            val issuerBank = success.formState.issuingBank
            if (issuerBank == null) {
                _effect.send(CardEditEffect.ShowError("카드사를 선택해주세요."))
                return@launch
            }

            paymentCardsRepository.updateCard(
                CreditCard(
                    id = card.id,
                    cardNumber = success.formState.cardNumber,
                    expiredDate = success.formState.expiredDate,
                    ownerName = success.formState.ownerName,
                    password = success.formState.password,
                    issuingBank = success.formState.issuingBank
                )
            )
            _effect.send(CardEditEffect.OnCardEditSaved)
        }
    }

    private fun isEqualCard(card: CreditCard, formState: PaymentCardFormState): Boolean {
        return card.cardNumber == formState.cardNumber &&
                card.expiredDate == formState.expiredDate &&
                card.ownerName == formState.ownerName &&
                card.password == formState.password &&
                card.issuingBank == formState.issuingBank
    }

    companion object {
        val PAYMENTS_CARD_REPOSITORY_KEY =
            object : CreationExtras.Key<PaymentCardsRepository> {}
        val CARD_ID_KEY = object : CreationExtras.Key<Long> {}


        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val paymentCardsRepository =
                    this[PAYMENTS_CARD_REPOSITORY_KEY] as PaymentCardsRepository
                val cardId = this[CARD_ID_KEY] as Long

                CardEditViewModel(
                    cardId = cardId,
                    paymentCardsRepository = paymentCardsRepository,
                )
            }
        }
    }
}
