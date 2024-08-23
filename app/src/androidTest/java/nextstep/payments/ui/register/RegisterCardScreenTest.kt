package nextstep.payments.ui.register

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.payments.model.OwnerNameValidResult
import org.junit.Rule
import org.junit.Test

class RegisterCardScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 카드번호를_입력하면_카드번호_형식에_맞게_변형되어_노출된다() {
        // given
        val cardNumber = "1234567890123456"
        val expectedFormattedCardNumber = "1234 - 5678 - 9012 - 3456"
        val uiState =
            RegisterCardUiState.NONE
                .copy(cardNumber = cardNumber)

        composeTestRule.setContent {
            RegisterCardScreen(
                uiState = uiState,
                navigateUp = {},
                onNewCardScreenEvent = {},
            )
        }

        // when & then
        composeTestRule
            .onNodeWithText(expectedFormattedCardNumber)
            .assertIsDisplayed()
    }

    @Test
    fun 카드번호_5자리_이상_입력되면_카드번호_형식에_맞게_변형되어_노출된다() {
        // given
        val cardNumber = "12345"
        val expectedFormattedCardNumber = "1234 - 5"
        val uiState =
            RegisterCardUiState.NONE
                .copy(cardNumber = cardNumber)

        composeTestRule.setContent {
            RegisterCardScreen(
                uiState = uiState,
                navigateUp = {},
                onNewCardScreenEvent = {},
            )
        }

        // then
        composeTestRule
            .onNodeWithText(expectedFormattedCardNumber)
            .assertIsDisplayed()
    }

    @Test
    fun 카드번호_4자리만_입력되면_숫자_4자리만_입력된다() {
        // given
        val cardNumber = "1234"
        val expectedFormattedCardNumber = "1234"

        val uiState =
            RegisterCardUiState.NONE
                .copy(cardNumber = cardNumber)

        composeTestRule.setContent {
            RegisterCardScreen(
                uiState = uiState,
                navigateUp = {},
                onNewCardScreenEvent = {},
            )
        }

        composeTestRule.waitForIdle()
        // then
        composeTestRule
            .onNodeWithText(expectedFormattedCardNumber)
            .assertIsDisplayed()
    }

    @Test
    fun 만료일를_입력하면_만료일_형식에_맞게_변형되어_노출된다() {
        // given
        val expiredDate = "1234"
        val expectedFormattedExpiredDate = "12 / 34"

        val uiState =
            RegisterCardUiState.NONE
                .copy(expiredDate = expiredDate)

        composeTestRule.setContent {
            RegisterCardScreen(
                uiState = uiState,
                navigateUp = {},
                onNewCardScreenEvent = {},
            )
        }

        // then
        composeTestRule
            .onNodeWithText(expectedFormattedExpiredDate)
            .assertIsDisplayed()
    }

    @Test
    fun 만료일_월과_연도_첫번째_자리_입력되면_만료일_형식에_맞게_변형되어_노출된다() {
        // given
        val expiredDate = "121"
        val expectedFormattedExpiredDate = "12 / 1"

        val uiState =
            RegisterCardUiState.NONE
                .copy(expiredDate = expiredDate)

        composeTestRule.setContent {
            RegisterCardScreen(
                uiState = uiState,
                navigateUp = {},
                onNewCardScreenEvent = {},
            )
        }

        // then
        composeTestRule
            .onNodeWithText(expectedFormattedExpiredDate)
            .assertIsDisplayed()
    }

    @Test
    fun 만료일에_월만_입력하면_월만_노출된다() {
        // given
        val expiredDate = "12"
        val expectedFormattedExpiredDate = "12"

        val uiState =
            RegisterCardUiState.NONE
                .copy(expiredDate = expiredDate)

        composeTestRule.setContent {
            RegisterCardScreen(
                uiState = uiState,
                navigateUp = {},
                onNewCardScreenEvent = {},
            )
        }

        // then
        composeTestRule
            .onNodeWithText(expectedFormattedExpiredDate)
            .assertIsDisplayed()
    }

    @Test
    fun 만료일_5글자_입력해도_4글자만_노출된다() {
        // given
        val expiredDate = "12345"
        val expectedFormattedExpiredDate = "12 / 34"

        val uiState =
            RegisterCardUiState.NONE
                .copy(expiredDate = expiredDate)

        composeTestRule.setContent {
            RegisterCardScreen(
                uiState = uiState,
                navigateUp = {},
                onNewCardScreenEvent = {},
            )
        }

        // then
        composeTestRule
            .onNodeWithText(expectedFormattedExpiredDate)
            .assertIsDisplayed()
    }

    @Test
    fun 카드_소유주_이름이_30자_이하이면_유효하다() {
        // given
        val ownerName = "123456789012345678901234567890"
        val validationResult = OwnerNameValidResult.VALID

        val uiState =
            RegisterCardUiState.NONE
                .copy(
                    ownerName = ownerName,
                    ownerNameValidResult = validationResult,
                )

        composeTestRule.setContent {
            RegisterCardScreen(
                uiState = uiState,
                navigateUp = {},
                onNewCardScreenEvent = {},
            )
        }

        // then
        composeTestRule
            .onNodeWithText(ERROR_OWNER_NAME_LENGTH)
            .assertIsNotDisplayed()
    }

    @Test
    fun 카드_소유주_이름이_30자_초과이면_소유주_이름_길이가_유효하지_않다() {
        // given
        val ownerName = "1234567890123456789012345678901"
        val validationResult = OwnerNameValidResult.ERROR_OWNER_NAME_LENGTH

        val uiState =
            RegisterCardUiState.NONE
                .copy(
                    ownerName = ownerName,
                    ownerNameValidResult = validationResult,
                )

        composeTestRule.setContent {
            RegisterCardScreen(
                uiState = uiState,
                navigateUp = {},
                onNewCardScreenEvent = {},
            )
        }

        // then
        composeTestRule
            .onNodeWithText(ERROR_OWNER_NAME_LENGTH)
            .assertIsDisplayed()
    }

    companion object {
        private const val ERROR_OWNER_NAME_LENGTH = "카드 소유자 이름은 30자 이하여야 합니다."
    }
}
