package nextstep.payments.component

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.requestFocus
import nextstep.payments.ui.newcard.component.CardOwnerNameTextField
import org.junit.Rule
import org.junit.Test

class CardOwnerNameTextFieldText {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `초기화면에_라벨이_보여야한다`() {
        val input = mutableStateOf("")

        composeTestRule.setContent {
            CardOwnerNameTextField(input.value, onValueChange = { input.value = it })
        }

        composeTestRule
            .onNodeWithText("카드 소유자 이름(선택)")
            .assertIsDisplayed()
    }

    @Test
    fun `입력창이_포커스됬을때_홀더의_내용이_보여야한다`() {
        val input = mutableStateOf("")

        composeTestRule.setContent {
            CardOwnerNameTextField(input.value, onValueChange = { input.value = it })
        }

        composeTestRule
            .onNodeWithContentDescription("CardOwnerNameTextField")
            .requestFocus()

        composeTestRule
            .onNodeWithText("카드에 표시된 이름을 입력하세요.")
            .assertIsDisplayed()
    }

    @Test
    fun `입력이_있으면_홀더의_내용이_보이지_않아야_한다`() {
        val input = mutableStateOf("12")

        composeTestRule.setContent {
            CardOwnerNameTextField(input.value, onValueChange = { input.value = it })
        }
        composeTestRule
            .onNodeWithText("카드에 표시된 이름을 입력하세요.")
            .assertIsNotDisplayed()
    }

}