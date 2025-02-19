package nextstep.payments.ui.new_card

import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NewCardViewModel : ViewModel() {

    private val _cardState = MutableStateFlow(NewCardState())
    val cardState = _cardState.asStateFlow()

    fun onAction(action: NewCardAction) {
        when (action) {
            is NewCardAction.OnCartNumberChange -> setCardNumber(action.cardNumber)
            is NewCardAction.OnExpiredDateChange -> setExpiredDate(action.expiredDate)
            is NewCardAction.OnOwnerNameChange -> setOwnerName(action.ownerName)
            is NewCardAction.OnPasswordChange -> setPassword(action.password)
        }
    }

    private fun setCardNumber(cardNumber: TextFieldValue) {
        if (cardNumber.text.length > 19 || !cardNumber.text.last().isDigit()) {
            return
        }
        _cardState.update {
            if (it.cardNumber.text.length % 5 == 3 && cardNumber.text.length % 5 == 4 && cardNumber.text.length != 19) {
                val newText = "${cardNumber.text}-"
                it.copy(
                    cardNumber = TextFieldValue(
                        text = newText,
                        selection = TextRange(newText.length)
                    )
                )
            } else {
                it.copy(cardNumber = cardNumber)
            }
        }
    }

    private fun setExpiredDate(expiredDate: TextFieldValue) {
        if (expiredDate.text.length > 5 || !expiredDate.text.last().isDigit()) {
            return
        }
        _cardState.update {
            if (it.expiredDate.text.length == 2 && expiredDate.text.length == 3) {
                val newText = "${expiredDate.text.substring(0, 2)}/${expiredDate.text[2]}"
                it.copy(
                    expiredDate = TextFieldValue(
                        text = newText,
                        selection = TextRange(newText.length)
                    )
                )
            } else {
                it.copy(expiredDate = expiredDate)
            }
        }
    }

    private fun setOwnerName(ownerName: String) {
        if (ownerName.length > 30) {
            return
        }
        _cardState.update {
            it.copy(ownerName = ownerName)
        }
    }

    private fun setPassword(password: String) {
        if (password.length > 4 || !password.last().isDigit()) {
            return
        }
        _cardState.update {
            it.copy(password = password)
        }
    }
}
