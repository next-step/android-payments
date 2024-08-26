package nextstep.payments.ui.creditcard.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import nextstep.payments.model.CardRegisterResult
import nextstep.payments.ui.creditcard.CreditCardRoute

const val ARG_CARD_REGISTER_RESULT = "cardRegisterResult"
const val CREDIT_CARD_ROUTE = "creditCard"

fun NavGraphBuilder.creditCardScreen(navigateToRegister: (String?) -> Unit) {
    composable(
        route = CREDIT_CARD_ROUTE,
        arguments =
            listOf(
                navArgument(ARG_CARD_REGISTER_RESULT) {
                    type = NavType.EnumType(CardRegisterResult::class.java)
                },
            ),
    ) { entry ->
        val cardRegisterResult =
            entry.savedStateHandle.get<CardRegisterResult>(ARG_CARD_REGISTER_RESULT)
        CreditCardRoute(
            cardRegisterResult = cardRegisterResult,
            navigateToRegister = navigateToRegister,
        )
    }
}
