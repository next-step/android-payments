package nextstep.payments

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import nextstep.payments.ui.screen.NewCardScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NewCardScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            NewCardScreen(
                navigateToCardList = {}
            )
        }
    }

    @Test
    fun 카드_번호_입력시_카드_번호_포맷에_맞게_입력된다() {
        // when
        val cardNumber = "1234567812345678"

        // then
        composeTestRule.onNodeWithText("카드 번호")
            .performTextInput(cardNumber)

        composeTestRule.onNodeWithText("카드 번호")
            .assert(hasText("1234 - 5678 - 1234 - 5678"))
    }

    @Test
    fun 카드_번호_입력시_16_자리를_넘어가지_않는다() {
        // when
        val cardNumber = "12345678123456781234"

        // then
        composeTestRule.onNodeWithText("카드 번호")
            .performTextInput(cardNumber) // 16자리 이상 입력

        // 입력된 카드 번호가 올바르게 변환되었는지 확인
        composeTestRule.onNodeWithText("카드 번호")
            .assert(hasText("1234 - 5678 - 1234 - 5678", ignoreCase = false)) // 변환된 값으로 비교
    }

    @Test
    fun 만료_일자_입력시_만료_일자_포맷에_맞게_입력된다() {
        // when
        val expiredDate = "1223"

        // then
        composeTestRule.onNodeWithText("만료일")
            .performTextInput(expiredDate)

        composeTestRule.onNodeWithText("만료일")
            .assert(hasText("12 / 23"))
    }

    @Test
    fun 만료_일자_입력시_4_자리를_넘어가지_않는다() {
        // when
        val expiredDate = "12234"

        // then
        composeTestRule.onNodeWithText("만료일")
            .performTextInput(expiredDate) // 4자리 이상 입력

        // 입력된 만료 일자가 올바르게 변환되었는지 확인
        composeTestRule.onNodeWithText("만료일")
            .assert(hasText("12 / 23", ignoreCase = false)) // 변환된 값으로 비교
    }

    @Test
    fun 카드_소유자_이름_입력시_카드_소유자_이름이_입력된다() {
        // when
        val ownerName = "홍길동"

        // then
        composeTestRule.onNodeWithText("카드 소유자 이름(선택)")
            .performTextInput(ownerName)

        composeTestRule.onNodeWithText("카드 소유자 이름(선택)")
            .assert(hasText(ownerName))
    }

    @Test
    fun 비밀번호_입력시_비밀번호가_입력된다() {
        // when
        val password = "1234"

        // then
        composeTestRule.onNodeWithText("비밀번호")
            .performTextInput(password)

        composeTestRule.onNodeWithText("비밀번호")
            .assert(hasText("••••"))
    }

    @Test
    fun 비밀번호_입력시_4_자리를_넘어가지_않는다() {
        // when
        val password = "12345"

        // then
        composeTestRule.onNodeWithText("비밀번호")
            .performTextInput(password)

        composeTestRule.onNodeWithText("비밀번호")
            .assert(hasText("••••"))
    }

    @Test
    fun 필수_필드_조건을_만족_못할시_스낵바를_호출한다() {
        // given
        val cardNumber = "1234567812345678"
        val expiredDate = "1223"
        val ownerName = "홍길동"
        val password = "12"

        // when
        composeTestRule.onNodeWithText("카드 번호")
            .performTextInput(cardNumber)

        composeTestRule.onNodeWithText("만료일")
            .performTextInput(expiredDate)

        composeTestRule.onNodeWithText("카드 소유자 이름(선택)")
            .performTextInput(ownerName)

        composeTestRule.onNodeWithText("비밀번호")
            .performTextInput(password)

        composeTestRule.onNodeWithContentDescription("addCardCheck")
            .performClick()

        // then
        composeTestRule.onNodeWithContentDescription("validateSnackbar")
            .assertIsDisplayed()
    }
}
