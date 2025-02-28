package nextstep.payments.cardlist

import nextstep.payments.base.ScreenEvent
import nextstep.payments.base.ScreenSideEffect
import nextstep.payments.base.ScreenState
import nextstep.payments.model.Card

data class CardListState(
    val cards: List<Card> = emptyList(),
): ScreenState {
    val currentCardsState
        get() = when(cards.size) {
            0 -> CardsState.NoCard
            1 -> CardsState.OneCard(cards.first())
            else -> CardsState.Cards(cards)
        }
}

sealed class CardListEvent: ScreenEvent {
    data object OnClickCreateCardButton: CardListEvent()
    data object OnCreateNewCard: CardListEvent()
}

sealed class CardListSideEffect: ScreenSideEffect {
    data object NavigateToNewCardScreen: CardListSideEffect()
}

sealed class CardsState {
    data object NoCard: CardsState()
    data class OneCard(val card: Card): CardsState()
    data class Cards(val cards: List<Card>): CardsState()
}
