package nextstep.payments.screens.card.update

import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import nextstep.payments.screens.card.state.CardCompanyState
import nextstep.payments.screens.card.state.CardState
import org.junit.Rule
import org.junit.Test

class EditCardScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 수정할카드의_변경사항이_없다면_완료버튼이_비활성화다() {
        // given
        composeTestRule
            .setContent {
                val card = CardState(
                    id = 0,
                    cardNumber = "1234567890123456",
                    expiredDate = "1225",
                    ownerName = "BANDAL",
                    password = "1234",
                    selectedCardCompany = CardCompanyState.HANA
                )
                EditCardScreen(
                    uiState = UpdateCardUiState.EditCardUiState(
                        cardState = card,
                        isFormValid = false,
                        cardUpdated = false,
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

    @Test
    fun 수정할카드의_변경사항이_있다면_완료버튼이_활성화다() {
        // given
        composeTestRule
            .setContent {
                val cardNumbers = "1234567890123456"
                val expiredDate = "1225"
                val ownerName = "BANDAL"
                val password = "1234"

                EditCardScreen(
                    uiState = UpdateCardUiState.EditCardUiState(
                        cardState = CardState(
                            id = 0,
                            cardNumber = cardNumbers,
                            expiredDate = expiredDate,
                            ownerName = ownerName,
                            password = password,
                            selectedCardCompany = CardCompanyState.KAKAO
                        ),
                        cardUpdated = false,
                        isFormValid = true,
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
}
