package nextstep.payments.ui

import androidx.compose.runtime.Composable
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
        composable(NavigationItem.PaymentCards.route) { navBackResult ->
            val newId = navBackResult.savedStateHandle.remove<String>("newPaymentId") ?: ""
            PaymentCardsScreenRoute(
                onAddCardClick = { navHostController.navigate(NavigationItem.AddCard.route) },
                newCardId = newId
            )
        }
        composable(NavigationItem.AddCard.route) {
            AddCardScreenRoute(
                onBackClick = { navHostController.popBackStack() },
                onBackWithAddCompleted = { newPaymentId ->
                    navHostController.previousBackStackEntry
                        ?.savedStateHandle
                        ?.set("newPaymentId", newPaymentId)
                    navHostController.popBackStack()
                }
            )
        }
    }
}
