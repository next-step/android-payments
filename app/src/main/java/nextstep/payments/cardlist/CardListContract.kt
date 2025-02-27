package nextstep.payments.cardlist

import nextstep.payments.base.ScreenEvent
import nextstep.payments.base.ScreenSideEffect
import nextstep.payments.base.ScreenState
import nextstep.payments.model.Card

data class CardListState(
    val cards: List<Card> = emptyList(),
): ScreenState {
    val currentCardCount
        get() = when(cards.size) {
            0 -> CardCount.NO_CARD
            1 -> CardCount.ONE_CARD
            else -> CardCount.CARDS
        }
}

sealed class CardListEvent: ScreenEvent {
    data object OnClickCreateCardButton: CardListEvent()
    data object OnCreateNewCard: CardListEvent()
}

sealed class CardListSideEffect: ScreenSideEffect {
    data object NavigateToNewCardScreen: CardListSideEffect()
}

enum class CardCount {
    NO_CARD, ONE_CARD, CARDS
}
