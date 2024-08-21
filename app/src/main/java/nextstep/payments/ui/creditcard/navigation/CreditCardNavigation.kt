package nextstep.payments.ui.creditcard.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import nextstep.payments.ui.creditcard.CreditCardRoute

const val CREDIT_CARD_ROUTE = "credit_card"
const val ARG_SHOULD_FETCH_CARDS = "shouldFetchCards"

fun NavGraphBuilder.creditCardScreen(onAddCardClick: () -> Unit) {
    composable(
        route = CREDIT_CARD_ROUTE,
        arguments =
            listOf(
                navArgument(ARG_SHOULD_FETCH_CARDS) {
                    type = NavType.BoolType
                    defaultValue = false
                },
            ),
    ) {
        CreditCardRoute(
            onAddCardClick = onAddCardClick,
        )
    }
}
