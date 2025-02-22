package nextstep.payments.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import nextstep.payments.ui.cardlist.component.AddCardButton
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class AddCardButtonTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `초기화면에_추가_아이콘이_보여야_한다`() {

        //given, when
        composeTestRule.setContent {
            AddCardButton(onAddClick = {})
        }

        //then
        composeTestRule
            .onNodeWithContentDescription("add_card_icon")
            .assertIsDisplayed()
    }

    @Test
    fun `추가_아이콘을_클릭시_추가_이벤트가_전달되어야_한다`() {

        //given
        var isSendAddEvent = false
        composeTestRule.setContent {
            AddCardButton(onAddClick = { isSendAddEvent = true })
        }

        //when
        composeTestRule
            .onNodeWithContentDescription("add_card_icon")
            .performClick()

        //then
        assertTrue(isSendAddEvent)
    }
}