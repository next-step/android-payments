package nextstep.payments.ui

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import nextstep.payments.ui.creditcard.navigation.ARG_CARD_REGISTER_RESULT
import nextstep.payments.ui.creditcard.navigation.CREDIT_CARD_ROUTE
import nextstep.payments.ui.creditcard.navigation.creditCardScreen
import nextstep.payments.ui.register.navigation.ARG_CARD_ID
import nextstep.payments.ui.register.navigation.REGISTER_CARD_ROUTE
import nextstep.payments.ui.register.navigation.registerCardScreen

@Composable
fun PaymentsApp(modifier: Modifier = Modifier) {
    PaymentsNav(modifier = modifier)
}

@Composable
private fun PaymentsNav(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = CREDIT_CARD_ROUTE,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        creditCardScreen(
            navigateToRegister = { cardId ->
                val route =
                    if (cardId == null) {
                        REGISTER_CARD_ROUTE
                    } else {
                        REGISTER_CARD_ROUTE.replace("{$ARG_CARD_ID}", cardId)
                    }
                navController.navigate(route)
            },
        )

        registerCardScreen(
            navigateToCredit = { result ->
                navController.previousBackStackEntry
                    ?.savedStateHandle
                    ?.set(
                        key = ARG_CARD_REGISTER_RESULT,
                        value = result,
                    )


                navController.popBackStack()
            },
        )
    }
}
