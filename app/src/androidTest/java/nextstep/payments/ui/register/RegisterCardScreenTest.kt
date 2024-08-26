package nextstep.payments.ui.register

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import nextstep.payments.model.OwnerNameValidResult
import org.junit.Rule
import org.junit.Test

class RegisterCardScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 카드_소유주_이름이_30자_이하이면_유효하다() {
        // given
        val ownerName = "123456789012345678901234567890"
        val validationResult = OwnerNameValidResult.VALID

        val uiState =
            RegisterCardUiState.DEFAULT_REGISTER
                .copyState(
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
            RegisterCardUiState.DEFAULT_REGISTER
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

    @Test
    fun 카드_등록_버튼이_활성화_된다() {
        // given
        val uiState =
            RegisterCardUiState.DEFAULT_REGISTER
                .copy(registerEnabled = true)

        composeTestRule.setContent {
            RegisterCardScreen(
                uiState = uiState,
                navigateUp = {},
                onNewCardScreenEvent = {},
            )
        }

        // then
        composeTestRule
            .onNodeWithTag("RegisterCardButton")
            .assertIsEnabled()
    }

    @Test
    fun 카드_등록_버튼이_비활성화_된다() {
        // given
        val uiState =
            RegisterCardUiState.DEFAULT_REGISTER
                .copy(registerEnabled = false)

        composeTestRule.setContent {
            RegisterCardScreen(
                uiState = uiState,
                navigateUp = {},
                onNewCardScreenEvent = {},
            )
        }

        // then
        composeTestRule
            .onNodeWithTag("RegisterCardButton")
            .assertIsNotEnabled()
    }

    companion object {
        private const val ERROR_OWNER_NAME_LENGTH = "카드 소유자 이름은 30자 이하여야 합니다."
    }
}
