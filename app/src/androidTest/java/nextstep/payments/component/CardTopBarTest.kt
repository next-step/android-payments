package nextstep.payments.component

import androidx.compose.material3.Text
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import junit.framework.TestCase.assertTrue
import nextstep.payments.designsystem.component.CardTopBar
import org.junit.Rule
import org.junit.Test

class CardTopBarTest {


    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun `지정한_타이틀이_잘_보여야_한다`() {

        //given, when
        composeTestRule.setContent {
            CardTopBar(
                title = {
                    Text("타이틀")
                },
                onSaveClick = {},
                onBackClick = {}
            )
        }
        //then
        composeTestRule
            .onNodeWithText("타이틀")
            .assertIsDisplayed()
    }

    @Test
    fun `초기화면에_완료버튼이_보여야_한다`() {
        //given, when
        composeTestRule.setContent {
            CardTopBar(
                title = {
                    Text("타이틀1")
                },
                onSaveClick = {},
                onBackClick = {}
            )
        }
        //then
        composeTestRule
            .onNodeWithContentDescription("완료")
            .assertIsDisplayed()
    }

    @Test
    fun `초기화면에_뒤로가기버튼이_보여야_한다`() {
        //given, when
        composeTestRule.setContent {
            CardTopBar(
                title = {
                    Text("타이틀2")
                },
                onSaveClick = {},
                onBackClick = {}
            )
        }
        //then
        composeTestRule
            .onNodeWithContentDescription("뒤로 가기")
            .assertIsDisplayed()
    }


    @Test
    fun `완료버튼을_눌렀을때_이벤트가_전달되어야_한다`() {
        //given
        var isSendSaveClickEvent = false
        composeTestRule.setContent {
            CardTopBar(
                title = {
                    Text("타이틀0")
                },
                onSaveClick = { isSendSaveClickEvent = true },
                onBackClick = {}
            )
        }

        //when
        composeTestRule
            .onNodeWithContentDescription("완료")
            .performClick()

        //then
        assertTrue(isSendSaveClickEvent)
    }

    @Test
    fun `뒤로가기버튼을_눌렀을때_이벤트가_전달되어야_한다`() {
        //given
        var isSendBackClickEvent = false
        composeTestRule.setContent {
            CardTopBar(
                title = {
                    Text("타이틀4")
                },
                onSaveClick = {},
                onBackClick = { isSendBackClickEvent = true }
            )
        }

        //when
        composeTestRule
            .onNodeWithContentDescription("뒤로 가기")
            .performClick()

        //then
        assertTrue(isSendBackClickEvent)
    }
}