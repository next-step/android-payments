package nextstep.payments.edit_card

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import nextstep.payments.data.BankType
import nextstep.payments.data.Card
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EditCardScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val initCard = Card(
        id = 0,
        cardNumber = "1111222233334444",
        expiredDate = "1212",
        ownerName = "테스트",
        password = "0000",
        bankType = BankType.BC
    )

    @Before
    fun setUp() {
        // given
        composeTestRule.setContent {
            var card by remember { mutableStateOf(initCard) }
            var isBottomSheetOpen by remember { mutableStateOf(false) }
            var isCompleteButtonEnabled by remember { mutableStateOf(card != initCard) }

            EditCardScreen(
                card = card,
                isBottomSheetOpen = isBottomSheetOpen,
                isCompleteButtonEnabled = isCompleteButtonEnabled,
                onBackClick = { },
                updateCard = { },
                setCardNumber = {
                    card = card.copy(cardNumber = it)
                    isCompleteButtonEnabled = card != initCard
                },
                setExpiredDate = {
                    card = card.copy(expiredDate = it)
                    isCompleteButtonEnabled = card != initCard
                },
                setOwnerName = {
                    card = card.copy(ownerName = it)
                    isCompleteButtonEnabled = card != initCard
                },
                setPassword = {
                    card = card.copy(password = it)
                    isCompleteButtonEnabled = card != initCard
                },
                setBankType = {
                    card = card.copy(bankType = it)
                    isCompleteButtonEnabled = card != initCard
                },
                setBottomSheetOpen = {
                    isBottomSheetOpen = it
                },
            )
        }
    }

    @Test
    fun `카드의_입력_정보가_변화가_없으면_카드_수정_버튼이_비활성화이다`() {
        composeTestRule
            .onNodeWithContentDescription("완료")
            .assertIsNotEnabled()
    }

    @Test
    fun `카드_번호가_변경되면_카드_수정_버튼_활성화된다`() {
        // when
        composeTestRule
            .onNodeWithText("카드 번호")
            .performTextClearance()

        composeTestRule
            .onNodeWithText("카드 번호")
            .performClick()
            .performTextInput("1234123412341234")

        composeTestRule.waitForIdle()

        composeTestRule
            .onNodeWithContentDescription("완료")
            .assertIsEnabled()
    }

    @Test
    fun `카드_만료일이_변경되면_카드_수정_버튼_활성화된다`() {
        // when
        composeTestRule
            .onNodeWithText("만료일")
            .performTextClearance()

        composeTestRule
            .onNodeWithText("만료일")
            .performClick()
            .performTextInput("1111")

        composeTestRule.waitForIdle()

        composeTestRule
            .onNodeWithContentDescription("완료")
            .assertIsEnabled()
    }

    @Test
    fun `카드_소유자_이름이_변경되면_카드_수정_버튼_활성화된다`() {
        // when
        composeTestRule
            .onNodeWithText("카드 소유자 이름(선택)")
            .performTextClearance()

        composeTestRule
            .onNodeWithText("카드 소유자 이름(선택)")
            .performClick()
            .performTextInput("이름변경")

        composeTestRule.waitForIdle()

        composeTestRule
            .onNodeWithContentDescription("완료")
            .assertIsEnabled()
    }

    @Test
    fun `카드_비밀번호가_변경되면_카드_수정_버튼_활성화된다`() {
        // when
        composeTestRule
            .onNodeWithText("비밀번호")
            .performTextClearance()

        composeTestRule
            .onNodeWithText("비밀번호")
            .performClick()
            .performTextInput("1234")

        composeTestRule.waitForIdle()

        composeTestRule
            .onNodeWithContentDescription("완료")
            .assertIsEnabled()
    }
}