package nextstep.payments

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import nextstep.payments.domain.Card
import nextstep.payments.screens.card.list.CardListScreen
import nextstep.payments.screens.card.list.CardListUiState
import org.junit.Rule
import org.junit.Test

class CardListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val fakeCards = listOf(
        Card(
            numbers = "1111222233334444",
            expiredDate = "0522",
            ownerName = "CREW",
            password = "0000"
        ),
        Card(
            numbers = "4444333322221111",
            expiredDate = "0421",
            ownerName = "BANDAL",
            password = "0000",
        ),
        Card(
            numbers = "1111222233444433",
            expiredDate = "0522",
            ownerName = "LACO",
            password = "0000"
        ),
        Card(
            numbers = "2211334411224455",
            expiredDate = "0421",
            ownerName = "LEAH",
            password = "0000",
        ),
        Card(
            numbers = "2211334411224454",
            expiredDate = "0421",
            ownerName = "BEOKBEOK",
            password = "0000",
        )
    )


    @Test
    fun 카드_목록이_비어있을_때에는_새로운_카드를_등록해주세요_안내가_노출되어야_한다() {
        // given
        composeTestRule.setContent {
            CardListScreen(
                state = CardListUiState.Empty,
                onAddCardClick = {},
            )
        }

        // then
        composeTestRule
            .onNodeWithText("새로운 카드를 등록해주세요")
            .assertExists()
        composeTestRule
            .onNodeWithText("추가")
            .assertDoesNotExist()
    }

    @Test
    fun 카드_목록에_카드가_한_개_있을_때의_카드_추가_UI는_목록_하단에_노출된다() {
        // given
        val state = CardListUiState.One(fakeCards.first())

        composeTestRule.setContent {
            CardListScreen(
                state = state,
                onAddCardClick = {},
            )
        }

        // then
        composeTestRule
            .onNodeWithContentDescription("카드추가")
            .assertExists()
        composeTestRule
            .onNodeWithText("추가")
            .assertDoesNotExist()
    }

    @Test
    fun 카드_목록에_카드가_여러_개_있을_때의_카드_추가_UI는_상단바에_노출된다() {
        // given
        val state = CardListUiState.Many(fakeCards)

        composeTestRule.setContent {
            CardListScreen(
                state = state,
                onAddCardClick = {},
            )
        }

        // then
        composeTestRule
            .onNodeWithContentDescription("카드추가")
            .assertDoesNotExist()
        composeTestRule
            .onNodeWithText("추가")
            .assertExists()
    }
}
