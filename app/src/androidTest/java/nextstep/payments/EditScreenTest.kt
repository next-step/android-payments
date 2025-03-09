package nextstep.payments

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import nextstep.payments.base.BaseComposableTest
import nextstep.payments.edit.EditEvent
import nextstep.payments.edit.EditScreen
import nextstep.payments.edit.EditState
import nextstep.payments.model.BankType
import nextstep.payments.model.Card
import org.junit.Test

class EditScreenTest : BaseComposableTest() {

    @Test
    fun `변경사항이_없으면_수정_완료_버튼이_활성화_되지_않는다`() {
        // given
        val originCard = Card(
            id = 1,
            cardNumber = "1234-1234-1234-1234",
            expiredDate = "12/12",
            ownerName = "홍길동",
            password = "1234",
            bankType = BankType.KB,
        )

        composeTestRule.setContent {
            var state by remember {
                mutableStateOf(
                    EditState(
                        card = Card(
                            id = originCard.id,
                            cardNumber = originCard.cardNumber,
                            expiredDate = originCard.expiredDate,
                            ownerName = originCard.ownerName,
                            password = originCard.password,
                            bankType = originCard.bankType,
                        ),
                        isEditEnabled = false,
                    )
                )
            }

            val handleEvent: (EditEvent) -> Unit = { event ->
                when (event) {
                    is EditEvent.OnCardNumberChange -> {
                        val newCardState = state.card.copy(cardNumber = event.cardNumber)
                        val isCardDataChanged = newCardState != originCard
                        state = state.copy(
                            card = newCardState,
                            isEditEnabled = isCardDataChanged,
                        )
                    }

                    else -> {}
                }
            }

            EditScreen(
                cardNumber = state.card.cardNumber,
                expiredDate = state.card.expiredDate,
                ownerName = state.card.ownerName,
                password = state.card.password,
                bankType = state.card.bankType,
                isEditEnabled = state.isEditEnabled,
                sendEvent = handleEvent,
            )
        }

        // when
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
        val originCard = Card(
            id = 1,
            cardNumber = "1234-1234-1234-1234",
            expiredDate = "12/12",
            ownerName = "홍길동",
            password = "1234",
            bankType = BankType.KB,
        )

        composeTestRule.setContent {
            var state by remember {
                mutableStateOf(
                    EditState(
                        card = Card(
                            id = originCard.id,
                            cardNumber = originCard.cardNumber,
                            expiredDate = originCard.expiredDate,
                            ownerName = originCard.ownerName,
                            password = originCard.password,
                            bankType = originCard.bankType,
                        ),
                        isEditEnabled = false,
                    )
                )
            }

            val handleEvent: (EditEvent) -> Unit = { event ->
                when (event) {
                    is EditEvent.OnCardNumberChange -> {
                        val newCardState = state.card.copy(cardNumber = event.cardNumber)
                        val isCardDataChanged = newCardState != originCard
                        state = state.copy(
                            card = newCardState,
                            isEditEnabled = isCardDataChanged,
                        )
                    }

                    else -> {}
                }
            }

            EditScreen(
                cardNumber = state.card.cardNumber,
                expiredDate = state.card.expiredDate,
                ownerName = state.card.ownerName,
                password = state.card.password,
                bankType = state.card.bankType,
                isEditEnabled = state.isEditEnabled,
                sendEvent = handleEvent,
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