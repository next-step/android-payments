package nextstep.payments

import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class RouteActivityTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun `카드추가_화면전환_이벤트가_발생시_카드추가_화면으로_이동해야_한다`() {
        //when
        composeTestRule.onNodeWithContentDescription("add_card_icon").performClick()

        composeTestRule.waitForIdle()

        //then
        composeTestRule.onNodeWithContentDescription("BankSelectBottomSheet").isDisplayed()
    }
}