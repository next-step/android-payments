package nextstep.payments.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import nextstep.payments.ui.screen.navigation.CardRoute


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardApp(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        modifier = modifier,
    ) {
        NavHost(
            navController = navController,
            startDestination = CardRoute.CARD_LIST.route,
            modifier = modifier
                .fillMaxSize()
                .padding(it)
        ) {
            composable(
                route = CardRoute.CARD_LIST.route
            ) {
                CardListScreen(
                    navigateToNewCard = {
                        navController.navigate(CardRoute.NEW_CARD.route)
                    }
                )
            }
            composable(
                route = CardRoute.NEW_CARD.route
            ) {
                NewCardScreen(
                    navigateToCardList = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CardAppPreview() {
    CardApp()
}