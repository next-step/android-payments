package nextstep.payments.ui.screen.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class CardRoute {

    @Serializable
    data object CardList : CardRoute()

    @Serializable
    data class NewCard(val cardId: String?) : CardRoute()
}

