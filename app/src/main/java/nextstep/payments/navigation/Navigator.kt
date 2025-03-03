package nextstep.payments.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import nextstep.payments.model.Card
import nextstep.payments.ui.cardlist.navigation.CardList
import nextstep.payments.ui.cardlist.navigation.routeToCardList
import nextstep.payments.ui.newcard.navigation.routeToNewCard
import nextstep.payments.ui.updatecard.navigation.routeToUpdateCard


@Composable
fun rememberNavigator(
    navController: NavHostController = rememberNavController()
): Navigator = remember(navController) {
    NavigatorImpl(navController)
}

@Stable
class NavigatorImpl(
    override val navController: NavHostController
) : Navigator {
    override val startDestination: Screen
        get() = CardList(isFetchCards = false)

    override val routeToNewCard: () -> Unit
        get() = {
            navController.routeToNewCard()
        }

    override val routeToUpdateCard: (Card) -> Unit
        get() = { card ->
            navController.routeToUpdateCard(card = card)
        }
    override val routeToCardList: (isFetchCards: Boolean) -> Unit
        get() = navController::routeToCardList
}


interface Navigator {

    val navController: NavHostController

    val startDestination: Screen

    val routeToNewCard: () -> Unit

    val routeToUpdateCard: (Card) -> Unit

    val routeToCardList: (isFetchCards: Boolean) -> Unit

}