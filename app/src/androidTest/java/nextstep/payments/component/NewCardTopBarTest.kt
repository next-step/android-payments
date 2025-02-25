package nextstep.payments.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.payments.ui.newcard.component.NewCardTopBar
import org.junit.Rule
import org.junit.Test

class NewCardTopBarTest {

    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun `초기화면에_카드_추가_텍스트가_보여야_한다`() {

        //given, when
        composeTestRule.setContent {
            NewCardTopBar(onSaveClick = {}, onBackClick = {})
        }

        //then
        composeTestRule
            .onNodeWithText("카드 추가")
            .assertIsDisplayed()
    }

}