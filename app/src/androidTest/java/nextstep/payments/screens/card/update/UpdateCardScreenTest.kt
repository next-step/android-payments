package nextstep.payments.screens.card.update

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.payments.screens.card.state.CardCompanyState
import nextstep.payments.screens.card.state.CardState
import org.junit.Rule
import org.junit.Test

class UpdateCardScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun UiState가_AddCardUiState일_때_새_카드를_등록하는_화면이_표시된다() {
        // given
        composeTestRule.setContent {
            UpdateCardScreen(
                uiState = UpdateCardUiState.AddCardUiState(),
                onCardCompanyClick = {},
                onCardNumberChange = {},
                onExpiredDateChange = {},
                onOwnerNameChange = {},
                onPasswordChange = {},
                onBackClick = {},
                onSaveClick = {},
            )
        }

        // then
        composeTestRule
            .onNodeWithText("카드 추가")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("카드 수정")
            .assertIsNotDisplayed()
    }

    @Test
    fun UiState가_EditCardUiState일_때_카드를_수정하는_화면이_표시된다() {
        // given
        composeTestRule.setContent {
            UpdateCardScreen(
                uiState = UpdateCardUiState.EditCardUiState(
                    cardForEdit = CardState(
                        cardNumber = "1234567890123456",
                        expiredDate = "1225",
                        ownerName = "BANDAL",
                        password = "1234",
                        selectedCardCompany = CardCompanyState.HANA,
                    )
                ),
                onCardCompanyClick = {},
                onCardNumberChange = {},
                onExpiredDateChange = {},
                onOwnerNameChange = {},
                onPasswordChange = {},
                onBackClick = {},
                onSaveClick = {},
            )
        }

        // then
        composeTestRule
            .onNodeWithText("카드 수정")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("카드 추가")
            .assertIsNotDisplayed()
    }
}
