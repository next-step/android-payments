package nextstep.payments.newcard

import nextstep.payments.base.BaseViewModel
import nextstep.payments.data.PaymentCardsRepository
import nextstep.payments.model.Card
import java.util.UUID.randomUUID

class NewCardViewModel(
    private val repository: PaymentCardsRepository = PaymentCardsRepository
) : BaseViewModel<NewCardState, NewCardEvent, NewCardSideEffect>() {
    override fun initState() = NewCardState()

    override fun handleEvent(event: NewCardEvent) {
        when (event) {
            is NewCardEvent.OnCardNumberChanged -> setCardNumber(event.cardNumber)
            is NewCardEvent.OnExpiredDateChanged -> setExpiredDate(event.expiredDate)
            is NewCardEvent.OnOwnerNameChanged -> setOwnerName(event.ownerName)
            is NewCardEvent.OnPasswordChanged -> setPassword(event.password)
            is NewCardEvent.OnClickBackButton -> sendSideEffect(NewCardSideEffect.PopBackStack)
            is NewCardEvent.OnClickCompleteButton -> createNewCard()
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

    private fun createNewCard() {
        val card = currentState().let {
            Card(
                id = randomUUID().hashCode(),
                cardNumber = it.cardNumber,
                expiredDate = it.expiredDate,
                ownerName = it.ownerName,
                password = it.password,
            )
        }
        repository.addCard(card)
        sendSideEffect(NewCardSideEffect.PopBackStackWithResult)
    }
}
