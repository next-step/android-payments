package nextstep.payments

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.payments.ui.card.newcard.component.NewCardTopBar
import org.junit.Rule
import org.junit.Test

class NewCardTopBarTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 수정모드_일때는_카드_수정으로_표시되어야_한다() {
        // given
        val isEdit = true
        composeTestRule.setContent {
            NewCardTopBar(isEdit = isEdit, onBackClick = { }, onSaveClick = { })
        }

        // when

        // then
        composeTestRule
            .onNodeWithText("카드 수정")
            .assertExists()
    }

    @Test
    fun 생성모드_일때는_카드_추가로_표시되어야_한다() {
        // given
        val isEdit = false
        composeTestRule.setContent {
            NewCardTopBar(isEdit = isEdit, onBackClick = { }, onSaveClick = { })
        }

        // when

        // then
        composeTestRule
            .onNodeWithText("카드 추가")
            .assertExists()
    }

}
