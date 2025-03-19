package nextstep.payments

import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import nextstep.payments.ui.newcard.NewCardScreen
import org.junit.Rule
import org.junit.Test

class NewCardScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `카드추가화면에_진입하면_카드선택_바텀시트가_표출된다`() {
        // Given,When: 카드 추가 화면에 진입
        composeTestRule.setContent {
            NewCardScreen({})
        }

        // Then: 카드 선택 바텀 시트가 표출
        composeTestRule
            .onNodeWithText("신한카드")
            .assertExists()
    }

    @Test
    fun `카드선택바텀시트_신한카드를_선택하면_바텀시트가_닫히고_신한카드가_카드에_표시된다`() {
        // Given: 카드 선택 바텀 시트가 표출된 상태
        composeTestRule.setContent {
            NewCardScreen({})
        }

        // When: 바텀 시트에서 신한카드를 선택
        composeTestRule.onNode(hasText("신한카드")).performClick()

        // Then: 바텀 시트가 닫히고, 신한카드가 카드에 표시
        composeTestRule
            .onNodeWithText("BC카드")
            .assertDoesNotExist()
        composeTestRule.onNode(hasText("신한카드")).assertExists()
    }
}
