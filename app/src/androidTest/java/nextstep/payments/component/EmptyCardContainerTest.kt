package nextstep.payments.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import nextstep.payments.ui.cardlist.component.EmptyCardContainer
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class EmptyCardContainerTest {

    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun `초기화면에_새로운_카드를_등록해주세요_텍스트가_보여야_한다`() {

        //given, when
        composeTestRule.setContent {
            EmptyCardContainer(onRouteToNewCard = {})
        }

        //then
        composeTestRule
            .onNodeWithText("새로운 카드를 등록해주세요")
            .assertIsDisplayed()
    }

    @Test
    fun `초기화면에_추가버튼이_보여야_한다`() {

        //given, when
        composeTestRule.setContent {
            EmptyCardContainer(onRouteToNewCard = {})
        }

        //then
        composeTestRule
            .onNodeWithContentDescription("add_card_icon")
            .assertIsDisplayed()
    }

    @Test
    fun `초기화면에_추가_텍스트가_보이지_않아야_한다`() {

        //given, when
        composeTestRule.setContent {
            EmptyCardContainer(onRouteToNewCard = {})
        }

        //then
        composeTestRule
            .onNodeWithText("추가")
            .assertIsNotDisplayed()
    }

    @Test
    fun `추가버튼을_클릭시_카드추가_화면으로_전환하는_이벤트가_전달되어야_한다`() {

        //given
        var isSendOnRouteToNewCardEvent = false
        composeTestRule.setContent {
            EmptyCardContainer(onRouteToNewCard = { isSendOnRouteToNewCardEvent = true })
        }

        //when
        composeTestRule
            .onNodeWithContentDescription("add_card_icon")
            .performClick()

        //then
        assertTrue(isSendOnRouteToNewCardEvent)
    }

}