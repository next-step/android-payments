package nextstep.payments.component

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.requestFocus
import nextstep.payments.ui.newcard.component.CardPasswordTextField
import org.junit.Rule
import org.junit.Test

class CardPasswordTextFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `초기화면에_라벨이_보여야한다`() {
        val input = mutableStateOf("")

        composeTestRule.setContent {
            CardPasswordTextField(input.value, onValueChange = { input.value = it })
        }

        composeTestRule
            .onNodeWithText("비밀번호")
            .assertIsDisplayed()
    }

    @Test
    fun `입력창이_포커스됬을때_홀더의_내용이_보여야한다`() {
        val input = mutableStateOf("")

        composeTestRule.setContent {
            CardPasswordTextField(input.value, onValueChange = { input.value = it })
        }

        composeTestRule
            .onNodeWithContentDescription("CardPasswordTextField")
            .requestFocus()

        composeTestRule
            .onNodeWithText("0000")
            .assertIsDisplayed()
    }

    @Test
    fun `입력이_있으면_홀더의_내용이_보이지_않아야_한다`() {
        val input = mutableStateOf("12")

        composeTestRule.setContent {
            CardPasswordTextField(input.value, onValueChange = { input.value = it })
        }
        composeTestRule
            .onNodeWithText("0000")
            .assertIsNotDisplayed()
    }

}