package nextstep.payments.ui.register.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import nextstep.payments.ui.register.RegisterCardRoute

const val REGISTER_CARD_ROUTE = "register_card"

fun NavGraphBuilder.registerCardScreen(navigateUp: (Boolean) -> Unit) {
    composable(route = REGISTER_CARD_ROUTE) {
        RegisterCardRoute(navigateUp = navigateUp)
    }
}
