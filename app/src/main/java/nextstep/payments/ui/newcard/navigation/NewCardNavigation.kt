package nextstep.payments.ui.newcard.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import nextstep.payments.ui.newcard.NewCardRoute
import nextstep.payments.ui.newcard.navigation.NewCardDestination.ROUTE

fun NavGraphBuilder.newCardScreen(
    onBackClick: () -> Unit,
) {
    composable(route = ROUTE) {
        NewCardRoute(
            onBackClick = onBackClick,
            onSaveClick = onBackClick,
        )
    }
}

fun NavController.navigateToNewCard() {
    navigate(route = ROUTE)
}

object NewCardDestination {
    const val ROUTE = "NewCardScreen"
}
