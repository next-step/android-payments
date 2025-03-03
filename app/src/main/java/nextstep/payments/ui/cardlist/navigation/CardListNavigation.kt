package nextstep.payments.ui.cardlist.navigation

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import nextstep.payments.model.Card
import nextstep.payments.navigation.Screen
import nextstep.payments.ui.cardlist.CardListScreen

fun NavController.routeToCardList(isFetchCards: Boolean) {
    navigate(CardList(isFetchCards)) {
        popUpTo(CardList(isFetchCards)) {
            inclusive = true
        }
    }
}

fun NavGraphBuilder.cardListNavGraph(
    onRouteToNewCard: () -> Unit,
    onRouteToUpdateCard: (Card) -> Unit
) {
    composable<CardList> {
        CardListScreen(
            onRouteToNewCard = onRouteToNewCard,
            onRouteToUpdateCard = onRouteToUpdateCard
        )
    }
}

@Serializable
data class CardList(val isFetchCards: Boolean) : Screen

private const val KEY_IS_FETCH_CARDS = "isFetchCards"

fun SavedStateHandle.isFetchCardStateFlow() =
    getStateFlow(KEY_IS_FETCH_CARDS, false)

fun SavedStateHandle.clearIsFetchCards() {
    this[KEY_IS_FETCH_CARDS] = false
}
