package nextstep.payments.card_list

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import nextstep.payments.cart_list.CardListTopAppbar
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class CardListTopAppBarTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun Payments_타이틀이_노출되어야_한다() {
        // given
        composeTestRule.setContent {
            CardListTopAppbar(
                onAddClick = {},
            )
        }

        // then
        composeTestRule
            .onNodeWithText("Payments")
            .assertExists()
    }

    @Test
    fun showAddTextButton_인자가_true이면_추가_텍스트버튼이_노출되어야_한다() {
        // given
        composeTestRule.setContent {
            CardListTopAppbar(
                onAddClick = {},
                showAddTextButton = true,
            )
        }

        // then
        composeTestRule
            .onNodeWithText("추가")
            .assertIsDisplayed()
    }

    @Test
    fun showAddTextButton_인자가_false이면_추가_텍스트버튼이_노출되지_않아야한다() {
        // given
        composeTestRule.setContent {
            CardListTopAppbar(
                onAddClick = {},
                showAddTextButton = false,
            )
        }

        // then
        composeTestRule
            .onNodeWithText("추가")
            .assertIsNotDisplayed()
    }

    @Test
    fun 추가버튼이_노출되어_있을_때_버튼이클릭_될_수_있다() {
        // given
        var isClicked = false

        composeTestRule.setContent {
            CardListTopAppbar(
                onAddClick = {
                    isClicked = true
                },
                showAddTextButton = true,
            )
        }

        // when
        composeTestRule
            .onNodeWithText("추가")
            .performClick()

        // then
        assertEquals(true, isClicked)
    }
}