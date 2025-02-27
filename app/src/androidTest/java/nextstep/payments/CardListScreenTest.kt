package nextstep.payments

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import nextstep.payments.base.BaseComposableTest
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
            CardListScreen(cards = cards)
        }

        // then
        composeTestRule
            .onNodeWithText("새로운 카드를 등록해주세요")
            .assertIsDisplayed()
    }
}
