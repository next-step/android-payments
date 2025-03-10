package nextstep.payments.ui.screen.navigation

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import nextstep.payments.data.model.Card
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Serializable
sealed class CardRoute(val route: String) {
    @Serializable
    data object CardList : CardRoute("cardList")

    @Serializable
    data object NewCard : CardRoute("newCard") {
        fun withArgs(card: Card?): String {
            val json = card?.let { Json.encodeToString(it) } ?: ""
            return "newCard?card=${json.encodeUrl()}"
        }
    }
}

fun String.encodeUrl(): String = URLEncoder.encode(this, StandardCharsets.UTF_8.toString())
