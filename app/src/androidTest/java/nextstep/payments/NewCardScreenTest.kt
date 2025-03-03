package nextstep.payments

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import nextstep.payments.base.BaseComposableTest
import nextstep.payments.newcard.NewCardScreen
import org.junit.Test

class NewCardScreenTest: BaseComposableTest() {

    @Test
    fun `카드_생성_화면_진입시_바텀시트가_보여진다`() {
        // given
        composeTestRule.setContent {
            NewCardScreen(
                popBackStack = {},
                popBackStackWithResult = {},
            )
        }
        // when

        // thena
        composeTestRule
            .onNodeWithTag("카드사 선택 바텀 시트")
            .assertIsDisplayed()
    }

    @Test
    fun `카드사를_선택하면_바텀시트가_닫힌다`() {
        // given
        composeTestRule.setContent {
            NewCardScreen(
                popBackStack = {},
                popBackStackWithResult = {},
            )
        }

        // when
        composeTestRule
            .onNodeWithTag("카드사 선택 버튼: 국민카드")
            .performClick()

        // then
        composeTestRule
            .onNodeWithTag("카드사 선택 바텀 시트")
            .assertIsNotDisplayed()
    }
}