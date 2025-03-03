package nextstep.payments.ui.newcard.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import nextstep.payments.navigation.Screen
import nextstep.payments.ui.newcard.NewCardScreen

fun NavController.routeToNewCard() {
    navigate(NewCard)
}

fun NavGraphBuilder.newCardNavGraph(
    onBackClick: () -> Unit,
    onRouteToCardList: () -> Unit,
) {
    composable<NewCard> {
        NewCardScreen(
            onBackClick = onBackClick,
            onRouteToCardList = onRouteToCardList
        )
    }
}

@Serializable
data object NewCard : Screen
