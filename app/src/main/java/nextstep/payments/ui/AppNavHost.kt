package nextstep.payments.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import nextstep.payments.ui.add.AddCardScreenRoute
import nextstep.payments.ui.navigation.NavigationItem
import nextstep.payments.ui.payments.PaymentCardsScreenRoute

@Composable
fun AppNavHost(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = NavigationItem.PaymentCards.route
    ) {
        composable(NavigationItem.PaymentCards.route) {
            PaymentCardsScreenRoute(
                onAddCardClick = { navHostController.navigate(NavigationItem.AddCard.route) }
            )
        }
        composable(NavigationItem.AddCard.route) {
            AddCardScreenRoute(
                onBackClick = { navHostController.popBackStack() },
                onBackWithAddCompleted = { navHostController.popBackStack() }
            )
        }
    }
}
