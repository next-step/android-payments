package nextstep.payments.cardlist

import nextstep.payments.base.BaseViewModel

class CardListViewModel: BaseViewModel<CardListState, CardListEvent, CardListSideEffect>() {
    override fun initState() = CardListState()

    override fun handleEvent(event: CardListEvent) {
        when (event) {
            is CardListEvent.OnClickCreateCardButton -> sendSideEffect(CardListSideEffect.NavigateToNewCardScreen)
        }
    }
}
