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

class CardNumberVisualTransformationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `1234_5678_1234_5678이_입력되면_1234_하이픈_5678_하이픈_1234_하이픈_5678_이다`() {
        // given
        composeTestRule.setContent {
            TestTextField(
                visualTransformation = CardNumberVisualTransformation(),
                tag = "cardNumberField"
            )
        }

        // when
        composeTestRule.onNodeWithTag("cardNumberField")
            .performTextInput("1234567812345678")
        composeTestRule.waitForIdle()

        // then
        composeTestRule.onNodeWithTag("cardNumberField")
            .assertTextEquals("1234-5678-1234-5678")
    }

    @Test
    fun `1234까지_입력되었다면_하이픈은_아직_없다`() {
        // given
        composeTestRule.setContent {
            TestTextField(
                visualTransformation = CardNumberVisualTransformation(),
                tag = "cardNumberField"
            )
        }

        // when
        composeTestRule.onNodeWithTag("cardNumberField")
            .performTextInput("1234")
        composeTestRule.waitForIdle()

        // then
        composeTestRule.onNodeWithTag("cardNumberField")
            .assertTextEquals("1234")
    }

    @Test
    fun `12345까지_입력되었다면_1234_하이픈_5이다`() {
        // given
        composeTestRule.setContent {
            TestTextField(
                visualTransformation = CardNumberVisualTransformation(),
                tag = "cardNumberField"
            )
        }

        // when
        composeTestRule.onNodeWithTag("cardNumberField")
            .performTextInput("12345")
        composeTestRule.waitForIdle()

        // then
        composeTestRule.onNodeWithTag("cardNumberField")
            .assertTextEquals("1234-5")
    }

    @Composable
    fun TestTextField(visualTransformation: VisualTransformation, tag: String) {
        val text = remember { mutableStateOf("") }

        BasicTextField(
            value = text.value,
            onValueChange = { text.value = it },
            visualTransformation = visualTransformation,
            modifier = Modifier.testTag(tag)  // 태그 부여
        )
    }
}
