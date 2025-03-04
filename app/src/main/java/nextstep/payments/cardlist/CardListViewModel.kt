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
            is CardListEvent.ReloadCardList -> loadCards()
            is CardListEvent.OnClickCardItem -> sendSideEffect(CardListSideEffect.NavigateToEditScreen(event.cardId))
        }
    }

    private fun loadCards() {
        val cards = repository.cards
        val cardState = when(cards.size) {
            0 -> CardsState.NoCard
            1 -> CardsState.OneCard(cards.first())
            else -> CardsState.Cards(cards)
        }

        updateState(currentState().copy(cardsState = cardState))
    }
}
