package nextstep.payments

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import nextstep.payments.data.model.Card
import nextstep.payments.data.repository.PaymentCardsRepository
import nextstep.payments.ui.CardCompanyType
import nextstep.payments.ui.screen.NewCardScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NewCardScreenUpdateTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        PaymentCardsRepository.clearCards()

        PaymentCardsRepository.addCard(
            Card(
                cardId = "fdsafefefew-ewfwefwe-fewf",
                cardNumber = "1234567812345678",
                expiredDate = "1223",
                ownerName = "홍길동",
                password = "1234",
                cardCompanyType = CardCompanyType.BC
            )
        )

        composeTestRule.setContent {
            NewCardScreen(
                navigateToCardList = {},
                cardId = "fdsafefefew-ewfwefwe-fewf",
            )
        }
    }

    @Test
    fun 카드_아이디가_존재시_앱바_제목이_카드_수정으로_변경된다() {

        composeTestRule.onNodeWithText("카드 수정")
            .assertExists()
    }

    @Test
    fun 카드_아이디가_존재시_카드_정보가_화면에_노출된다() {

        composeTestRule.onNodeWithText("카드 번호")
            .assert(hasText("1234 - 5678 - 1234 - 5678"))

        composeTestRule.onNodeWithText("만료일")
            .assert(hasText("12 / 23"))

        composeTestRule.onNodeWithText("카드 소유자 이름(선택)")
            .assert(hasText("홍길동"))

        composeTestRule.onNodeWithText("비밀번호")
            .assert(hasText("••••"))

        composeTestRule.onNodeWithText("BC카드")
            .assertExists()
    }

    @Test
    fun 카드_수정시_변경사항이_없으면_수정_불가_스낵바를_호출한다() {
        composeTestRule.onNodeWithContentDescription("addCardCheck")
            .performClick()

        // then
        composeTestRule.onNodeWithContentDescription("validateSnackbar")
            .assertIsDisplayed()
    }
}
