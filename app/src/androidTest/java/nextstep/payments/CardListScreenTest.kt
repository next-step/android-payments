package nextstep.payments

import androidx.compose.ui.test.assertAny
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.filter
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.onChild
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import nextstep.payments.base.BaseComposableTest
import nextstep.payments.cardlist.CardsState
import nextstep.payments.cardlist.CardListScreen
import nextstep.payments.model.Card
import org.junit.Test

class CardListScreenTest : BaseComposableTest() {

    @Test
    fun `카드_목록이_비어있을_때에는_새로운_카드를_등록해주세요_안내가_노출되어야_한다`() {
        // given
        val cards = emptyList<Card>()

        // when
        composeTestRule.setContent {
            CardListScreen(
                cardsState = CardsState.NoCard,
                sendEvent = {},
            )
        }

        // then
        composeTestRule
            .onNodeWithText("새로운 카드를 등록해주세요", useUnmergedTree = true)
            .assertIsDisplayed()
    }

    @Test
    fun `카드_목록에_카드가_한_개_있을_때_카드_추가_UI가_노출된다`() {
        // given
        val card = Card(
            id = 1,
            cardNumber = "1234-5678-1234-5678",
            expiredDate = "12/34",
            ownerName = "홍길동",
            password = "1234",
        )

        // when
        composeTestRule.setContent {
            CardListScreen(
                cardsState = CardsState.OneCard(card),
                sendEvent = {},
            )
        }

        // then
        composeTestRule
            .onNodeWithTag("CreateCardButton")
            .assertIsDisplayed()
    }

    @Test
    fun `카드_목록에_카드가_여러_개_있을_때_카드_추가_UI가_노출되지_않는다`() {
        // given
        val cards = listOf(
            Card(
                id = 1,
                cardNumber = "1234-5678-1234-5678",
                expiredDate = "12/34",
                ownerName = "홍길동",
                password = "1234",
            ),
            Card(
                id = 2,
                cardNumber = "1234-5678-1234-5678",
                expiredDate = "12/34",
                ownerName = "홍길동",
                password = "1234",
            ),
            Card(
                id = 3,
                cardNumber = "1234-5678-1234-5678",
                expiredDate = "12/34",
                ownerName = "홍길동",
                password = "1234",
            )
        )

        // when
        composeTestRule.setContent {
            CardListScreen(
                cardsState = CardsState.Cards(cards),
                sendEvent = {},
            )
        }

        // then
        composeTestRule
            .onNodeWithTag("CreateCardButton", useUnmergedTree = true)
            .assertIsNotDisplayed()
    }

    @Test
    fun `카드_목록에_카드가_여러_개_있을_때_상단_바에_카드_추가_버튼이_노출된다`() {
        // given
        val cards = listOf(
            Card(
                id = 1,
                cardNumber = "1234-5678-1234-5678",
                expiredDate = "12/34",
                ownerName = "홍길동",
                password = "1234",
            ),
            Card(
                id = 2,
                cardNumber = "1234-5678-1234-5678",
                expiredDate = "12/34",
                ownerName = "홍길동",
                password = "1234",
            ),
            Card(
                id = 3,
                cardNumber = "1234-5678-1234-5678",
                expiredDate = "12/34",
                ownerName = "홍길동",
                password = "1234",
            )
        )

        // when
        composeTestRule.setContent {
            CardListScreen(
                cardsState = CardsState.Cards(cards),
                sendEvent = {}
            )
        }

        // then
        composeTestRule
            .onNodeWithTag("CardListTopBar")
            .onChildren()
            .filter(hasText("추가"))
            .assertCountEquals(1)
    }

    @Test
    fun `카드_목록에_카드가_한_개_있을_때_상단_바에_카드_추가_버튼이_노출되지_않는다`() {
        // given
        val card = Card(
            id = 1,
            cardNumber = "1234-5678-1234-5678",
            expiredDate = "12/34",
            ownerName = "홍길동",
            password = "1234",
        )

        // when
        composeTestRule.setContent {
            CardListScreen(
                cardsState = CardsState.OneCard(card),
                sendEvent = {},
            )
        }

        // then
        composeTestRule
            .onNodeWithTag("CardListTopBar")
            .onChildren()
            .filter(hasText("추가"))
            .assertCountEquals(0)
    }
}
