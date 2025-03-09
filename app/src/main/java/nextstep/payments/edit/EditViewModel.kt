package nextstep.payments.edit

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import nextstep.payments.base.BaseViewModel
import nextstep.payments.data.PaymentCardsRepository
import nextstep.payments.model.Card

class EditViewModel(
    private val cardId: Int,
    private val repository: PaymentCardsRepository = PaymentCardsRepository
) : BaseViewModel<EditState, EditEvent, EditSideEffect>() {

    init {
        repository.getCard(cardId)?.let {
            updateState { copy(card = it) }
        }
    }

    override fun initState(): EditState = EditState(card = Card.empty())

    override fun handleEvent(event: EditEvent) {
        when(event) {
            is EditEvent.OnCardNumberChange -> setCardNumber(event.cardNumber)
            is EditEvent.OnExpiredDateChange -> setExpiredDate(event.expiredDate)
            is EditEvent.OnOwnerNameChange -> setOwnerName(event.ownerName)
            is EditEvent.OnPasswordChange -> setPassword(event.password)
            EditEvent.OnClickBackButton -> sendSideEffect(EditSideEffect.NavigateBack)
            EditEvent.OnClickCompleteButton -> updateCard()
        }
    }

    private fun checkCardDataChanged(card: Card): Boolean {
        val originCard = repository.getCard(cardId)
        return originCard != card
    }

    private fun setCardNumber(cardNumber: String) {
        val newCardState = currentState().card.copy(cardNumber = cardNumber)
        val isCardChanged = checkCardDataChanged(newCardState)
        updateState(currentState().copy(card = newCardState, isEditEnabled = isCardChanged))
    }

    private fun setExpiredDate(expiredDate: String) {
        val newCardState = currentState().card.copy(expiredDate = expiredDate)
        val isCardChanged = checkCardDataChanged(newCardState)
        updateState(currentState().copy(card = newCardState, isEditEnabled = isCardChanged))
    }

    private fun setOwnerName(ownerName: String) {
        val newCardState = currentState().card.copy(ownerName = ownerName)
        val isCardChanged = checkCardDataChanged(newCardState)
        updateState(currentState().copy(card = newCardState, isEditEnabled = isCardChanged))
    }

    private fun setPassword(password: String) {
        val newCardState = currentState().card.copy(password = password)
        val isCardChanged = checkCardDataChanged(newCardState)
        updateState(currentState().copy(card = newCardState, isEditEnabled = isCardChanged))
    }

    private fun updateCard() {
        repository.setCard(currentState().card)
        sendSideEffect(EditSideEffect.NavigateBackWithNeedReload)
    }

    companion object {
        fun getFactory(cardInt: Int): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                EditViewModel(cardInt)
            }
        }
    }
}