package nextstep.payments

import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import nextstep.payments.base.BaseComposableTest
import nextstep.payments.data.PaymentCardsRepository
import nextstep.payments.edit.EditScreen
import nextstep.payments.edit.EditViewModel
import nextstep.payments.model.BankType
import nextstep.payments.model.Card
import org.junit.Test

class EditScreenTest : BaseComposableTest() {

    @Test
    fun `변경사항이_없으면_수정_완료_버튼이_활성화_되지_않는다`() {
        // given
        val paymentCardsRepository = PaymentCardsRepository
        paymentCardsRepository.addCard(
            Card(
                id = 1,
                cardNumber = "1234-1234-1234-1234",
                expiredDate = "12/12",
                ownerName = "홍길동",
                password = "1234",
                bankType = BankType.KB
            )
        )

        // when
        composeTestRule.setContent {
            EditScreen(
                popBackStack = {},
                popBackStackWithResult = {},
                viewModel = EditViewModel(
                    cardId = 1,
                    repository = paymentCardsRepository
                ),
            )
        }

        composeTestRule
            .onNodeWithTag("cardNumberTextField")
            .performTextClearance()

        composeTestRule
            .onNodeWithTag("cardNumberTextField")
            .performTextInput("1234-1234-1234-1234")

        // then
        composeTestRule.onNodeWithContentDescription("완료")
            .assertIsNotEnabled()
    }

    @Test
    fun `변경사항이_있으면_수정_완료_버튼이_활성화_된다`() {
        // given
        val paymentCardsRepository = PaymentCardsRepository
        paymentCardsRepository.addCard(
            Card(
                id = 1,
                cardNumber = "1234-1234-1234-1234",
                expiredDate = "12/12",
                ownerName = "홍길동",
                password = "1234",
                bankType = BankType.KB
            )
        )

        // when
        composeTestRule.setContent {
            EditScreen(
                popBackStack = {},
                popBackStackWithResult = {},
                viewModel = EditViewModel(
                    cardId = 1,
                    repository = paymentCardsRepository
                ),
            )
        }

        composeTestRule
            .onNodeWithTag("cardNumberTextField")
            .performTextClearance()

        composeTestRule
            .onNodeWithTag("cardNumberTextField")
            .performTextInput("9999-9999-9999-9999")

        // then
        composeTestRule.onNodeWithContentDescription("완료")
            .assertIsEnabled()
    }
}