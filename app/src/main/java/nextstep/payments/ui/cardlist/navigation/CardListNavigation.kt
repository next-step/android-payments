package nextstep.payments.ui.cardlist.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import nextstep.payments.ui.cardlist.CardListRoute
import nextstep.payments.ui.cardlist.navigation.CardListNavigation.ROUTE

fun NavGraphBuilder.cardListScreen(modifier: Modifier = Modifier) {
    composable(route = ROUTE) {
        CardListRoute()
    }
}

fun NavController.navigateToCardList() {
    navigate(route = ROUTE)
}

object CardListNavigation {
    const val ROUTE = "CardListScreen"
}
