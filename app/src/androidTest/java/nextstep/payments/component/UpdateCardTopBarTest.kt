package nextstep.payments.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.payments.ui.updatecard.component.UpdateCardTopBar
import org.junit.Rule
import org.junit.Test

class UpdateCardTopBarTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `초기화면에_카드_수정_텍스트가_보여야_한다`() {

        //given, when
        composeTestRule.setContent {
            UpdateCardTopBar(onSaveClick = {}, onBackClick = {})
        }

        //then
        composeTestRule
            .onNodeWithText("카드 수정")
            .assertIsDisplayed()
    }
}