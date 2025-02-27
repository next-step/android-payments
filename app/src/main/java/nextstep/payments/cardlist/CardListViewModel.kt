package nextstep.payments.cardlist

import nextstep.payments.base.BaseViewModel
import nextstep.payments.data.PaymentCardsRepository

class CardListViewModel(
    private val repository: PaymentCardsRepository = PaymentCardsRepository,
): BaseViewModel<CardListState, CardListEvent, CardListSideEffect>() {
    override fun initState() = CardListState()

    override fun handleEvent(event: CardListEvent) {
        when (event) {
            is CardListEvent.OnClickCreateCardButton -> sendSideEffect(CardListSideEffect.NavigateToNewCardScreen)
            is CardListEvent.OnCreateNewCard -> updateState(currentState().copy(cards = repository.cards))
        }
    }
}
