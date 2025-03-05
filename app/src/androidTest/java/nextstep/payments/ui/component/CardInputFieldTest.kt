package nextstep.payments.ui.component

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsFocused
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertIsNotFocused
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.requestFocus
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import nextstep.payments.data.model.Card
import nextstep.payments.data.model.CardCompany
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.math.max

class CardInputFieldTest {
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
                maxLength = 4,
                labelText = "라벨",
                placeHolderText = "플레이스홀더",
                keyboardType = KeyboardType.Text,
                visualTransformation = VisualTransformation.None,
                modifier = Modifier.testTag("inputField")
            )
        }
    }

    @Test
    fun 입력창에_포커스가_없고_값이_없으면_입력창에_라벨_노출() {
        // when
        // nothing

        // then
        composeTestRule.onNodeWithTag("inputField")
            .assertIsNotFocused()
            .assertIsDisplayed()
    }

    @Test
    fun 입력창에_포커스가_있고_값이_없으면_입력창에_플레이스홀더_노출_상단에_라벨_노출() {
        // when
        composeTestRule
            .onNodeWithTag("inputField")
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
            .onNodeWithTag("inputField")
            .performTextInput("안녕")

        // then
        composeTestRule.onNodeWithText("플레이스홀더")
            .assertIsNotDisplayed()
        composeTestRule.onNodeWithText("라벨")
            .assertIsDisplayed()
        composeTestRule.onNodeWithText("안녕")
            .assertIsDisplayed()
    }

    @Test
    fun 입력창에_maxLength_초과해서_값_입력시_초과한_문자열은_미노출() {
        // when
        composeTestRule
            .onNodeWithTag("inputField")
            .performTextInput("안녕하세요")

        // then
        composeTestRule.onNodeWithText("안녕하세")
            .assertIsDisplayed()
        composeTestRule.onNodeWithText("요", substring = true)
            .assertIsNotDisplayed()
    }
}