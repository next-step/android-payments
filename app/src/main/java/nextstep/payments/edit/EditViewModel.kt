package nextstep.payments.edit

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import nextstep.payments.base.BaseViewModel
import nextstep.payments.data.PaymentCardsRepository

class EditViewModel(
    private val cardId: Int,
    private val repository: PaymentCardsRepository = PaymentCardsRepository
) : BaseViewModel<EditState, EditEvent, EditSideEffect>() {

    init {
        repository.getCard(cardId)?.let {
            updateState {
                copy(
                    id = it.id,
                    cardNumber = it.cardNumber,
                    expiredDate = it.expiredDate,
                    ownerName = it.ownerName,
                    password = it.password,
                    bankType = it.bankType
                )
            }
        }
    }

    override fun initState(): EditState = EditState(id = cardId)

    override fun handleEvent(event: EditEvent) {
        when(event) {
            is EditEvent.OnCardNumberChange -> setCardNumber(event.cardNumber)
            is EditEvent.OnExpiredDateChange -> setExpiredDate(event.expiredDate)
            is EditEvent.OnOwnerNameChange -> setOwnerName(event.ownerName)
            is EditEvent.OnPasswordChange -> setPassword(event.password)
            EditEvent.OnClickBackButton -> sendSideEffect(EditSideEffect.NavigateBack)
            EditEvent.OnClickCompleteButton -> sendSideEffect(EditSideEffect.NavigateBackWithNeedReload)
        }
    }

    private fun setCardNumber(cardNumber: String) {
        updateState(currentState().copy(cardNumber = cardNumber))
    }

    private fun setExpiredDate(expiredDate: String) {
        updateState(currentState().copy(expiredDate = expiredDate))
    }

    private fun setOwnerName(ownerName: String) {
        updateState(currentState().copy(ownerName = ownerName))
    }

    private fun setPassword(password: String) {
        updateState(currentState().copy(password = password))
    }

    companion object {
        fun getFactory(cardInt: Int): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                EditViewModel(cardInt)
            }
        }
    }
}