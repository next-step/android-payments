package nextstep.payments.ui.screen.navigation

import kotlinx.serialization.Serializable

sealed class CardRoute {
    @Serializable
    data object CardList : CardRoute()

    @Serializable
    data object NewCard : CardRoute()
}