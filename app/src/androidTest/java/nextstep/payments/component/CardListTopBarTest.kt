package nextstep.payments.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import nextstep.payments.ui.cardlist.component.CardListTopBar
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class CardListTopBarTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `초기화면에_타이틀이_보여야_한다`() {

        //given,when
        composeTestRule.setContent {
            CardListTopBar(onAddClick = {})
        }

        //then
        composeTestRule
            .onNodeWithText("Payments")
            .assertIsDisplayed()
    }

    @Test
    fun `isShowAddText가_true인경우_추가_텍스트가_보여야_한다`() {

        //given,when
        composeTestRule.setContent {
            CardListTopBar(onAddClick = {}, isShowAddText = true)
        }


        //then
        composeTestRule
            .onNodeWithText("추가")
            .assertIsDisplayed()

    }

    @Test
    fun `isShowAddText가_false인경우_추가_텍스트가_보이지_않아야_한다`() {

        //given,when
        composeTestRule.setContent {
            CardListTopBar(onAddClick = {}, isShowAddText = false)
        }


        //then
        composeTestRule
            .onNodeWithText("추가")
            .assertIsNotDisplayed()

    }

    @Test
    fun `추가텍스트_클릭시_추가이벤트가_전달되어야_한다`() {

        //given
        var isSendAddEvent = false
        composeTestRule.setContent {
            CardListTopBar(onAddClick = {
                isSendAddEvent = true
            }, isShowAddText = true)
        }

        //when
        composeTestRule
            .onNodeWithText("추가")
            .performClick()

        //then
        assertTrue(isSendAddEvent)
    }
}