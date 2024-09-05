package nextstep.payments.ui.component.text.input

import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.text.input.VisualTransformation
import org.junit.Rule
import org.junit.Test

class ExpirationDateVisualTransformationTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `0924로_입력되면_09_슬래시_24_로_변환된다`() {
        // Given: ExpirationDateVisualTransformation이 적용된 TextField가 있다.
        composeTestRule.setContent {
            TestTextField(ExpirationDateVisualTransformation(), tag = "expirationDateField")
        }

        // When: "0924"를 입력하면
        composeTestRule.onNodeWithTag("expirationDateField")
            .performTextInput("0924")

        composeTestRule.waitForIdle()

        // Then: 변환된 텍스트가 "09 / 24"로 보여야 한다.
        composeTestRule.onNodeWithTag("expirationDateField")
            .assertTextEquals("09 / 24")
    }

    @Test
    fun `092로_입력되면_09_슬래시_2_로_변환된다`() {
        // Given: ExpirationDateVisualTransformation이 적용된 TextField가 있다.
        composeTestRule.setContent {
            TestTextField(ExpirationDateVisualTransformation(), tag = "expirationDateField")
        }

        // When: "092"를 입력하면
        composeTestRule.onNodeWithTag("expirationDateField")
            .performTextInput("092")

        composeTestRule.waitForIdle()

        // Then: 변환된 텍스트가 "09 / 2"로 보여야 한다.
        composeTestRule.onNodeWithTag("expirationDateField")
            .assertTextEquals("09 / 2")
    }

    @Test
    fun `09로_입력되면_09_슬래시_로_변환된다`() {
        // Given: ExpirationDateVisualTransformation이 적용된 TextField가 있다.
        composeTestRule.setContent {
            TestTextField(ExpirationDateVisualTransformation(), tag = "expirationDateField")
        }

        // When: "09"를 입력하면
        composeTestRule.onNodeWithTag("expirationDateField")
            .performTextInput("09")

        composeTestRule.waitForIdle()

        // Then: 변환된 텍스트가 "09 / "로 보여야 한다.
        composeTestRule.onNodeWithTag("expirationDateField")
            .assertTextEquals("09 / ")
    }

    @Composable
    fun TestTextField(visualTransformation: VisualTransformation, tag: String) {
        val text = remember { mutableStateOf("") }

        BasicTextField(
            value = text.value,
            onValueChange = { text.value = it },
            visualTransformation = visualTransformation,
            modifier = Modifier.testTag(tag) // 태그 부여
        )
    }
}
