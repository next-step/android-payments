package nextstep.payments.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import nextstep.payments.navigation.Navigator
import nextstep.payments.navigation.rememberNavigator
import nextstep.payments.ui.cardlist.navigation.cardListNavGraph
import nextstep.payments.ui.newcard.navigation.newCardNavGraph
import nextstep.payments.ui.updatecard.navigation.updateCardNavGraph


@Composable
fun MainScreen(
    navigator: Navigator = rememberNavigator(),
) {
    Box(modifier = Modifier.fillMaxSize()) {
        NavHost(
            navController = navigator.navController,
            startDestination = navigator.startDestination
        ) {
            cardListNavGraph(
                onRouteToNewCard = navigator.routeToNewCard,
                onRouteToUpdateCard = navigator.routeToUpdateCard
            )
            newCardNavGraph(
                onBackClick = navigator.navController::popBackStack,
                onRouteToCardList = { navigator.routeToCardList(true) }
            )
            updateCardNavGraph(
                onBackClick = navigator.navController::popBackStack,
                onUpdate = { navigator.routeToCardList(true) }
            )
        }
    }
}