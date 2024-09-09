package nextstep.payments.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import nextstep.payments.ui.cardlist.navigation.CardListNavigation
import nextstep.payments.ui.cardlist.navigation.cardListScreen
import nextstep.payments.ui.newcard.navigation.newCardScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: String = CardListNavigation.ROUTE,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        newCardScreen()
        cardListScreen()
    }
}
