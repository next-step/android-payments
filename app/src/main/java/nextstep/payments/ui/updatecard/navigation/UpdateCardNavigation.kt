package nextstep.payments.ui.updatecard.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import nextstep.payments.model.Card
import nextstep.payments.navigation.Screen
import nextstep.payments.ui.updatecard.UpdateCardScreen
import nextstep.payments.util.JsonConfig

fun NavController.routeToUpdateCard(card: Card) {
    navigate(UpdateCard(encodeCardString = JsonConfig.json.encodeToString(card)))
}

fun NavGraphBuilder.updateCardNavGraph(
    onBackClick: () -> Unit,
    onUpdate: () -> Unit,
) {
    composable<UpdateCard> {
        UpdateCardScreen(onBackClick = onBackClick, onUpdate = onUpdate)
    }
}

@Serializable
data class UpdateCard(val encodeCardString: String) : Screen {
    fun decodeCard(): Card = JsonConfig.json.decodeFromString(encodeCardString)
}
