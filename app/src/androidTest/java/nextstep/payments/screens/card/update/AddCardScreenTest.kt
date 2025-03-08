package nextstep.payments.screens.card.update

import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import nextstep.payments.screens.card.state.CardCompanyState
import nextstep.payments.screens.card.state.CardState
import org.junit.Rule
import org.junit.Test

class AddCardScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 카드를_추가할_때_카드_정보를_모두_입력한_상태라면_카드추가_버튼이_활성화된다() {
        // given
        composeTestRule
            .setContent {
                AddCardScreen(
                    uiState = UpdateCardUiState.AddCardUiState(
                        cardState = CardState(
                            selectedCardCompany = CardCompanyState.KB,
                            cardNumber = "1234567890123456",
                            expiredDate = "1225",
                            ownerName = "BANDAL",
                            password = "1234"
                        ),
                        cardUpdated = false
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
            .onNodeWithContentDescription("완료")
            .assertIsEnabled()
    }

    @Test
    fun 카드를_추가할_때_카드_정보가_하나라도_미기입_상태라면_카드추가_버튼이_비활성화된다() {
        // given
        composeTestRule
            .setContent {
                AddCardScreen(
                    uiState = UpdateCardUiState.AddCardUiState(
                        cardState = CardState(
                            selectedCardCompany = CardCompanyState.KB,
                            cardNumber = "1234567890123456",
                            expiredDate = "1225",
                            ownerName = "BANDAL",
                            password = ""
                        ),
                        cardUpdated = false
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
            .onNodeWithContentDescription("완료")
            .assertIsNotEnabled()
    }
}
