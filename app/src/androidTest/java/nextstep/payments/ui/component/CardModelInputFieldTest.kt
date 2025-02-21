package nextstep.payments.ui.component

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsFocused
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertIsNotFocused
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.requestFocus
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CardModelInputFieldTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private var value by mutableStateOf("")


    @Before
    fun setup() {
        // given
        composeTestRule.setContent {
            CardInputField(
                value = value,
                onValueChange = { value = it },
                labelText = "라벨",
                placeHolderText = "플레이스홀더",
                modifier = Modifier.testTag("cardInputField")
            )
        }
    }

    @Test
    fun 입력창에_포커스가_없고_값이_없으면_입력창에_라벨_노출() {
        // when
        // nothing

        // then
        composeTestRule.onNodeWithText("라벨")
            .assertIsNotFocused()
            .assertIsDisplayed()
    }

    @Test
    fun 입력창에_포커스가_있고_값이_없으면_입력창에_플레이스홀더_노출_상단에_라벨_노출() {
        // when
        composeTestRule.onNodeWithTag("cardInputField")
            .requestFocus()

        // then
        composeTestRule.onNodeWithText("플레이스홀더")
            .assertIsFocused()
            .assertIsDisplayed()
        composeTestRule.onNodeWithText("라벨")
            .assertIsFocused()
            .assertIsDisplayed()
    }

    @Test
    fun 입력창에_값을_입력하면_플레이스홀더_미노출_라벨_노출_입력한값이_노출() {
        // when
        composeTestRule
            .onNodeWithTag("cardInputField")
            .performTextInput("안녕")

        // then
        composeTestRule.onNodeWithText("플레이스홀더")
            .assertIsNotDisplayed()
        composeTestRule.onNodeWithText("라벨")
            .assertIsDisplayed()
        composeTestRule.onNodeWithText("안녕")
            .assertIsDisplayed()
    }
}