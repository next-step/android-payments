package nextstep.payments.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import nextstep.payments.ui.screen.NewCardScreenRoute
import nextstep.payments.ui.screen.PaymentCardsScreenRoute

@Composable
fun PaymentsNavigationHost(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = NavigationModel.PaymentCards.route
    ) {
        composable(NavigationModel.PaymentCards.route) { navBackResult ->
            PaymentCardsScreenRoute(
                onAddCardClick = { navHostController.navigate(NavigationModel.AddPaymentCard.route) },
            )
        }
        composable(NavigationModel.AddPaymentCard.route) {
            NewCardScreenRoute(
                onBackClick = { navHostController.popBackStack() },
                onAddComplete = { navHostController.popBackStack() }
            )
        }
    }
}
