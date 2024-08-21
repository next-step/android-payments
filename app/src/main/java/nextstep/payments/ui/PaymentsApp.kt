package nextstep.payments.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import nextstep.payments.ui.list.navigation.ARG_SHOULD_FETCH_CARDS
import nextstep.payments.ui.list.navigation.PAYMENT_LIST_ROUTE
import nextstep.payments.ui.list.navigation.paymentListScreen
import nextstep.payments.ui.newcard.navigation.newCardGScreen

@Composable
fun PaymentsApp(modifier: Modifier = Modifier) {
    PaymentsNav(modifier = modifier)
}

@Composable
private fun PaymentsNav(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = PAYMENT_LIST_ROUTE,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        paymentListScreen()

        newCardGScreen(
            navigateUp = { shouldFetchCards ->
                navController.previousBackStackEntry
                    ?.savedStateHandle
                    ?.set(
                        key = ARG_SHOULD_FETCH_CARDS,
                        value = shouldFetchCards,
                    )
                navController.popBackStack()
            },
        )
    }
}
