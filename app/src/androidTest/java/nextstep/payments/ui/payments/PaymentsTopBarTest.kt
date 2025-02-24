package nextstep.payments.ui.payments

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class PaymentsTopBarTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    
    @Test
    fun 추가가_불가능한_상태라면_추가_버튼이_보이지_않는다() {
        // given
        composeTestRule.setContent {
            PaymentsTopBar(isAddable = false)
        }

        // then
        composeTestRule.onNodeWithText("추가").assertDoesNotExist()
    }

    @Test
    fun 추가가_가능한_상태라면_추가_버튼이_보인다() {
        // given
        composeTestRule.setContent {
            PaymentsTopBar(isAddable = true)
        }

        // then
        composeTestRule.onNodeWithText("추가").assertExists()
    }
}