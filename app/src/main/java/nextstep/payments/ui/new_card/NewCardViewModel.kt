package nextstep.payments.ui.new_card

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

    private fun setCardNumber(cardNumber: String) {
        _cardState.update {
            it.copy(cardNumber = cardNumber)
        }
    }

    private fun setExpiredDate(expiredDate: String) {
        _cardState.update {
            it.copy(expiredDate = expiredDate)
        }
    }

    private fun setOwnerName(ownerName: String) {
        _cardState.update {
            it.copy(ownerName = ownerName)
        }
    }

    private fun setPassword(password: String) {
        _cardState.update {
            it.copy(password = password)
        }
    }
}
