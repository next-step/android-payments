package nextstep.payments.ui.newcard.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import nextstep.payments.ui.newcard.NewCardRoute

const val NEW_CARD_ROUTE = "new_card"

fun NavGraphBuilder.newCardGScreen() {
    composable(route = NEW_CARD_ROUTE) {
        NewCardRoute()
    }
}
