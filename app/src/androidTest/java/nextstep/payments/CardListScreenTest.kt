package nextstep.payments

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import nextstep.payments.base.BaseComposableTest
import nextstep.payments.cardlist.CardCount
import nextstep.payments.cardlist.CardListScreen
import nextstep.payments.model.Card
import org.junit.Test

class CardListScreenTest: BaseComposableTest() {

    @Test
    fun `카드_목록이_비어있을_때에는_새로운_카드를_등록해주세요_안내가_노출되어야_한다`() {
        // given
        val cards = emptyList<Card>()

        // when
        composeTestRule.setContent {
            CardListScreen(
                cards = cards,
                cardCount = CardCount.NO_CARD,
            )
        }

        // then
        composeTestRule
            .onNodeWithText("새로운 카드를 등록해주세요")
            .assertIsDisplayed()
    }

    @Test
    fun `카드_목록에_카드가_한_개_있을_때_카드_추가_UI가_노출된다`() {
        // given
        val cards = listOf(
            Card(
                id = 1,
                cardNumber = "1234-5678-1234-5678",
                expiredDate = "12/34",
                ownerName = "홍길동",
                password = "1234",
            )
        )

        // when
        composeTestRule.setContent {
            CardListScreen(
                cards = cards,
                cardCount = CardCount.ONE_CARD,
            )
        }

        // then
        composeTestRule
            .onNodeWithTag("CreateCardButton")
            .assertIsDisplayed()
    }
}
