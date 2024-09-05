package nextstep.payments.ui.newcard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.click
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTouchInput
import org.junit.Rule
import org.junit.Test

class NewCardScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `NewCardScreen에_isShowBanks가_true이면_바텀시트가_보인다`() {
        // given: isShowBanks = true
        val isShowBanks = true

        // when:
        val uiState = NewCardUiState.default
        composeTestRule.setContent {
            NewCardScreen(
                cardNumber = uiState.cardNumber,
                expiredDate = uiState.expirationDate,
                ownerName = uiState.ownerName,
                password = uiState.password,
                bank = uiState.selectedBank,
                isShowBanks = isShowBanks,
                setCardNumber = {},
                setExpiredDate = {},
                setOwnerName = {},
                setPassword = {},
                onAddCardClick = {},
                onBackClick = {},
                onBankClick = {}
            )
        }
        composeTestRule.waitForIdle()

        // then: 바텀시트 보이는지 확인
        composeTestRule
            .onNodeWithContentDescription("BankBottomSheet")
            .assertIsDisplayed()
    }

    @Test
    fun `NewCardScreen에_isShowBanks가_false이면_바텀시트가_보이지_않는다`() {
        // given: isShowBanks = false
        val isShowBanks = false

        // when:
        val uiState = NewCardUiState.default
        composeTestRule.setContent {
            NewCardScreen(
                cardNumber = uiState.cardNumber,
                expiredDate = uiState.expirationDate,
                ownerName = uiState.ownerName,
                password = uiState.password,
                bank = uiState.selectedBank,
                isShowBanks = isShowBanks,
                setCardNumber = {},
                setExpiredDate = {},
                setOwnerName = {},
                setPassword = {},
                onAddCardClick = {},
                onBackClick = {},
                onBankClick = {}
            )
        }
        composeTestRule.waitForIdle()

        // then: 바텀시트 보이는지 확인
        composeTestRule
            .onNodeWithContentDescription("BankBottomSheet")
            .assertIsNotDisplayed()
    }

    // TODO: 바텀시트 외부를 클릭해도 바텀시트가 내려가지 않는지 테스트
}