package nextstep.payments.ui.cardlist.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import nextstep.payments.ui.cardlist.CardListRoute
import nextstep.payments.ui.cardlist.navigation.CardListNavigation.ROUTE

fun NavGraphBuilder.cardListScreen(
    onAddCardClick: () -> Unit,
) {
    composable(route = ROUTE) {
        CardListRoute(onAddCardClick = onAddCardClick)
    }
}

fun NavController.navigateToCardList() {
    navigate(route = ROUTE)
}

object CardListNavigation {
    const val ROUTE = "CardListScreen"
}
