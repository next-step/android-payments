package nextstep.payments

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import nextstep.payments.model.CreditCard
import nextstep.payments.model.ValidationResult
import nextstep.payments.ui.newcard.NewCardInputValidator
import nextstep.payments.model.Card

class NewCardViewModel(
    private val repository: PaymentCardsRepository = PaymentCardsRepository,
) : ViewModel() {

    private val _cards = MutableStateFlow<List<Card>>(repository.cards)
    val cards: StateFlow<List<Card>> = _cards.asStateFlow()

    private val _cardAdded = MutableStateFlow<Boolean>(false)
    val cardAdded: StateFlow<Boolean> = _cardAdded.asStateFlow()

    private var cardNumberValidateResult = ValidationResult.ADDITIONAL_INPUT_REQUIRED
    private var ownerNameValidateResult = ValidationResult.ADDITIONAL_INPUT_REQUIRED
    private var expiredDateValidateResult = ValidationResult.ADDITIONAL_INPUT_REQUIRED
    private var passwordValidateResult = ValidationResult.ADDITIONAL_INPUT_REQUIRED

    private fun resetValidationResults() {
        cardNumberValidateResult = ValidationResult.ADDITIONAL_INPUT_REQUIRED
        ownerNameValidateResult = ValidationResult.ADDITIONAL_INPUT_REQUIRED
        expiredDateValidateResult = ValidationResult.ADDITIONAL_INPUT_REQUIRED
        passwordValidateResult = ValidationResult.ADDITIONAL_INPUT_REQUIRED
    }

    private fun resetCardInput() {
        _cardNumber.value = ""
        _expiredDate.value = ""
        _ownerName.value = ""
        _password.value = ""
    }

    fun resetAddCard() {
        _cardAdded.value = false
    }

    fun addCard() {
        if (cardNumberValidateResult == ValidationResult.SUCCESS
            && ownerNameValidateResult == ValidationResult.SUCCESS
            && expiredDateValidateResult == ValidationResult.SUCCESS
            && passwordValidateResult == ValidationResult.SUCCESS
        ) {
            repository.addCard(
                CreditCard(
                    _cardNumber.value,
                    _expiredDate.value,
                    _ownerName.value,
                    _password.value
                )
            )
        }
        _cardAdded.value = true
        resetValidationResults()
        resetCardInput()
    }

    private val _cardNumber = MutableStateFlow("")
    val cardNumber: StateFlow<String> = _cardNumber.asStateFlow()

    private val _expiredDate = MutableStateFlow("")
    val expiredDate: StateFlow<String> = _expiredDate.asStateFlow()

    private val _ownerName = MutableStateFlow("")
    val ownerName: StateFlow<String> = _ownerName.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    fun setCardNumber(cardNumber: String) {
        val validationResult = NewCardInputValidator.validateCardNumber(cardNumber)
        if (validationResult == ValidationResult.INPUT_REJECTED)
            return
        cardNumberValidateResult = validationResult
        _cardNumber.value = cardNumber
    }

    fun setExpiredDate(expiredDate: String) {
        val validationResult = NewCardInputValidator.validateExpiredDate(expiredDate)
        if (validationResult == ValidationResult.INPUT_REJECTED)
            return
        expiredDateValidateResult = validationResult
        _expiredDate.value = expiredDate
    }

    fun setOwnerName(ownerName: String) {
        val validationResult = NewCardInputValidator.validateOwnerName(ownerName)
        if (validationResult == ValidationResult.INPUT_REJECTED)
            return
        ownerNameValidateResult = validationResult
        _ownerName.value = ownerName
    }

    fun setPassword(password: String) {
        val validationResult = NewCardInputValidator.validatePassword(password)
        if (validationResult == ValidationResult.INPUT_REJECTED)
            return
        passwordValidateResult = validationResult
        _password.value = password
    }
}