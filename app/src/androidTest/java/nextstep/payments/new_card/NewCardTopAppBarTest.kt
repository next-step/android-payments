package nextstep.payments.new_card

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class NewCardTopAppBarTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 타이틀에_카드_추가_텍스트가_노출되어야_한다() {
        // given
        composeTestRule.setContent {
            NewCardTopAppBar(
                onBackClick = {},
                onSaveClick = {},
            )
        }

        // then
        composeTestRule
            .onNodeWithText("카드 추가")
            .assertExists()
    }

    @Test
    fun 뒤로가기_버튼_클릭이_되어야_한다() {
        // given
        var isClicked = false

        composeTestRule.setContent {
            NewCardTopAppBar(
                onBackClick = {
                    isClicked = true
                },
                onSaveClick = {},
            )
        }

        // when
        composeTestRule
            .onNodeWithContentDescription("뒤로 가기")
            .performClick()

        // then
        assertEquals(true, isClicked)
    }

    @Test
    fun 추가_버튼_클릭이_되어야_한다() {
        // given
        var isClicked = false

        composeTestRule.setContent {
            NewCardTopAppBar(
                onBackClick = {},
                onSaveClick = {
                    isClicked = true
                },
            )
        }

        // when
        composeTestRule
            .onNodeWithContentDescription("완료")
            .performClick()

        // then
        assertEquals(true, isClicked)
    }
}