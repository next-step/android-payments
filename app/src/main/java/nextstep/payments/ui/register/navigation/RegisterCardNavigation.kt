package nextstep.payments.ui.register.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import nextstep.payments.model.CardRegisterResult
import nextstep.payments.ui.register.RegisterCardRoute

const val ARG_CARD_ID = "cardId"
const val REGISTER_CARD_ROUTE = "register_card/{$ARG_CARD_ID}"

fun NavGraphBuilder.registerCardScreen(navigateToCredit: (CardRegisterResult) -> Unit) {
    composable(
        route = REGISTER_CARD_ROUTE,
        arguments =
            listOf(
                navArgument(ARG_CARD_ID) {
                    type = NavType.StringType
                    nullable = true
                },
            ),
    ) {
        RegisterCardRoute(navigateToCredit = navigateToCredit)
    }
}
