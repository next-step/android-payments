package nextstep.payments.new_card

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NewCardScreenStatefulTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            NewCardScreen(
                onBackButtonClick = { },
                navigateToCardList = { },
                viewModel = NewCardViewModel()
            )
        }
    }

    @Test
    fun `카드_번호_입력_필드가_노출되어야_한다`() {
        // then
        composeTestRule
            .onNodeWithText("카드 번호")
            .assertIsDisplayed()
    }

    @Test
    fun `만료일_입력_필드가_노출되어야_한다`() {
        // then
        composeTestRule
            .onNodeWithText("만료일")
            .assertIsDisplayed()
    }

    @Test
    fun `카드_소유자_입력_필드가_노출되어야_한다`() {
        // then
        composeTestRule
            .onNodeWithText("카드 소유자 이름(선택)")
            .assertIsDisplayed()
    }

    @Test
    fun `비밀번호_입력_필드가_노출되어야_한다`() {
        composeTestRule
            .onNodeWithText("비밀번호")
            .assertIsDisplayed()
    }

    @Test
    fun `카드_번호를_입력하면_입력값이_노출되어야_한다`() {
        // when
        composeTestRule
            .onNodeWithText("카드 번호")
            .performTextInput("0000")

        // then
        composeTestRule
            .onNodeWithText("카드 번호")
            .assertTextContains("0000")
    }

    @Test
    fun `만료일를_입력하면_입력값이_노출되어야_한다`() {
        // when
        composeTestRule
            .onNodeWithText("만료일")
            .performTextInput("00")

        // then
        composeTestRule
            .onNodeWithText("만료일")
            .assertTextContains("00")
    }

    @Test
    fun `카드_소유자를_입력하면_입력값이_노출되어야_한다`() {
        // when
        composeTestRule
            .onNodeWithText("카드 소유자 이름(선택)")
            .performTextInput("홍길동")

        // then
        composeTestRule
            .onNodeWithText("카드 소유자 이름(선택)")
            .assertTextContains("홍길동")
    }

    @Test
    fun `비밀번호를_입력하면_입력값이_마스킹되서_노출되어야_한다`() {
        // when
        composeTestRule
            .onNodeWithText("비밀번호")
            .performTextInput("0000")

        // then
        composeTestRule
            .onNodeWithText("비밀번호")
            .assertTextContains("••••")
    }
}