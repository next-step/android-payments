package nextstep.payments.cardlist

import nextstep.payments.base.ScreenEvent
import nextstep.payments.base.ScreenSideEffect
import nextstep.payments.base.ScreenState
import nextstep.payments.model.Card

data class CardListState(
    val cardsState: CardsState = CardsState.NoCard,
): ScreenState

sealed class CardListEvent: ScreenEvent {
    data object OnClickCreateCardButton: CardListEvent()
    data object ReloadCardList: CardListEvent()
}

sealed class CardListSideEffect: ScreenSideEffect {
    data object NavigateToNewCardScreen: CardListSideEffect()
}

sealed class CardsState {
    data object NoCard: CardsState()
    data class OneCard(val card: Card): CardsState()
    data class Cards(val cards: List<Card>): CardsState()
}
