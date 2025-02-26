package nextstep.payments.newcard

import nextstep.payments.base.BaseViewModel

class NewCardViewModel : BaseViewModel<NewCardState, NewCardEvent, NewCardSideEffect>() {
    override fun initState() = NewCardState()

    override fun handleEvent(event: NewCardEvent) {
        when (event) {
            is NewCardEvent.OnCardNumberChanged -> setCardNumber(event.cardNumber)
            is NewCardEvent.OnExpiredDateChanged -> setExpiredDate(event.expiredDate)
            is NewCardEvent.OnOwnerNameChanged -> setOwnerName(event.ownerName)
            is NewCardEvent.OnPasswordChanged -> setPassword(event.password)
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
}
