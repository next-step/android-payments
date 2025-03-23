package nextstep.payments.navigate

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import nextstep.payments.NewCardViewModel
import nextstep.payments.ui.CreditCardUiState
import nextstep.payments.ui.newcard.NewCardScreen
import nextstep.payments.ui.paymentcards.PaymentCardsScreen

@Composable
fun NavigateScreen(
    newCardViewModel: NewCardViewModel,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Destination.PAYMENTS.name,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(Destination.PAYMENTS.name) {
            PaymentCardsScreen(
                uiState = CreditCardUiState.Empty,
                onAddClick = {
                    navController.navigate(Destination.ADD_CARD.name)
                },
                modifier = Modifier.fillMaxSize()
            )
        }
        composable(Destination.ADD_CARD.name) {
            NewCardScreen(
                viewModel = newCardViewModel,
                navigateToCardList = {
                    navController.popBackStack()
                    newCardViewModel.resetAddCard()
                },
            )
        }
    }
}