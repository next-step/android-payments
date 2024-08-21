package nextstep.payments.ui.list.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import nextstep.payments.ui.list.PaymentListScreen

const val PAYMENT_LIST_ROUTE = "payment_list"
const val ARG_SHOULD_FETCH_CARDS = "shouldFetchCards"

fun NavGraphBuilder.paymentListScreen() {
    composable(
        route = PAYMENT_LIST_ROUTE,
        arguments =
            listOf(
                navArgument(ARG_SHOULD_FETCH_CARDS) {
                    type = NavType.BoolType
                    defaultValue = false
                },
            ),
    ) {
        PaymentListScreen()
    }
}
