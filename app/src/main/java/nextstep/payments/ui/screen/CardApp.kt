package nextstep.payments.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import nextstep.payments.ui.screen.navigation.CardRoute


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardApp(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()

    Scaffold(
        modifier = modifier,
    ) {
        NavHost(
            navController = navController,
            startDestination = CardRoute.CardList.route,
            modifier = modifier
                .fillMaxSize()
                .padding(it)
        ) {
            composable(CardRoute.CardList.route) {
                CardListScreen(
                    navigateToNewCard = {
                        navController.navigate(CardRoute.NewCard.route)
                    },
                    navigateToUpdateCard = { cardId ->
                        navController.navigate(CardRoute.NewCard.withId(cardId))
                    }
                )
            }

            composable(
                route = "${CardRoute.NewCard.route}?cardId={cardId}",
                arguments = listOf(navArgument("cardId") { nullable = true })
            ) { backStackEntry ->
                val cardId = backStackEntry.arguments?.getString("cardId")

                NewCardScreen(
                    cardId = cardId,
                    navigateToCardList = { navController.navigateUp() }
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
