package nextstep.payments.newcard.component.newcard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.text.input.PasswordVisualTransformation
import nextstep.payments.ui.newcard.component.NewCardTextField
import nextstep.payments.ui.newcard.component.NewCardVisualTransformation.CreditCardVisualTransformation
import nextstep.payments.ui.newcard.component.NewCardVisualTransformation.ExpiredDateVisualTransformation
import org.junit.Rule
import org.junit.Test

class NewCardListNavigationTestTextFieldTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun 카드_번호_입력_시_4자리_마다_대시_문자가_삽입된다() {
        // given:
        var input by mutableStateOf("")

        composeRule.setContent {
            NewCardTextField(
                label = "카드 번호",
                placeHolder = "카드 번호",
                text = input,
                setText = { input = it },
                visualTransformation = CreditCardVisualTransformation,
                modifier = Modifier.testTag("CardNumberTextField"),
            )
        }

        // when:
        val cardNumber = "1234123412341234"
        composeRule.onNodeWithTag("CardNumberTextField").performTextInput(cardNumber)

        // then:
        val expected = "1234-1234-1234-1234"
        val actual = composeRule.onNodeWithTag("CardNumberTextField")
            .fetchSemanticsNode().config.getOrNull(SemanticsProperties.EditableText)!!

        assert(actual[4] == expected[4])
        assert(actual[9] == expected[9])
        assert(actual[14] == expected[14])
    }

    @Test
    fun 만료_일_입력_시_연도와_월_사이에_슬래시_문자가_삽입된다() {
        // given:
        var input by mutableStateOf("")

        composeRule.setContent {
            NewCardTextField(
                label = "카드 번호",
                placeHolder = "카드 번호",
                text = input,
                setText = { input = it },
                visualTransformation = ExpiredDateVisualTransformation,
                modifier = Modifier.testTag("ExpiredDateTextField"),
            )
        }

        // when:
        val expiredDate = "1225"
        composeRule.onNodeWithTag("ExpiredDateTextField").performTextInput(expiredDate)

        // then:
        val expected = "12/25"
        val actual = composeRule.onNodeWithTag("ExpiredDateTextField")
            .fetchSemanticsNode().config.getOrNull(SemanticsProperties.EditableText)!!

        assert(actual[2] == expected[2])
    }

    @Test
    fun 비밀번호는_암호화_되어있다() {
        // given:
        var input by mutableStateOf("")

        composeRule.setContent {
            NewCardTextField(
                label = "카드 번호",
                placeHolder = "카드 번호",
                text = input,
                setText = { input = it },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.testTag("PasswordTextField")
            )
        }

        // when:
        val password = "1234"
        composeRule.onNodeWithTag("PasswordTextField").performTextInput(password)

        // then:
        composeRule.onNodeWithText(password).assertIsNotDisplayed()
    }
}
