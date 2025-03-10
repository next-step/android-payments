package nextstep.payments.ui.screen.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class CardRoute(val route: String) {
    @Serializable
    data object CardList : CardRoute("cardList")

    @Serializable
    data object NewCard : CardRoute("newCard") {
        fun withId(cardId: String?): String {
            return "newCard?cardId=${cardId}"
        }
    }
}

