package nextstep.payments.edit_card

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
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

    private var card by mutableStateOf(initCard)
    private var isBottomSheetOpen by mutableStateOf(false)

    @Before
    fun setUp() {
        card = initCard
        isBottomSheetOpen = false

        // given
        composeTestRule.setContent {
            val isCompleteButtonEnabled by remember { derivedStateOf { initCard != card } }

            EditCardScreen(
                card = card,
                isBottomSheetOpen = isBottomSheetOpen,
                isCompleteButtonEnabled = isCompleteButtonEnabled,
                onBackClick = {},
                updateCard = {},
                setCardNumber = {},
                setExpiredDate = {},
                setOwnerName = {},
                setPassword = {},
                setBankType = {},
                setBottomSheetOpen = {},
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
        card = card.copy(cardNumber = "1234123412341234")

        composeTestRule
            .onNodeWithContentDescription("완료")
            .assertIsEnabled()
    }

    @Test
    fun `카드_만료일이_변경되면_카드_수정_버튼_활성화된다`() {
        // when
        card = card.copy(expiredDate = "1111")

        composeTestRule
            .onNodeWithContentDescription("완료")
            .assertIsEnabled()
    }

    @Test
    fun `카드_소유자_이름이_변경되면_카드_수정_버튼_활성화된다`() {
        // when
        card = card.copy(ownerName = "이름변경")

        composeTestRule
            .onNodeWithContentDescription("완료")
            .assertIsEnabled()
    }

    @Test
    fun `카드_비밀번호가_변경되면_카드_수정_버튼_활성화된다`() {
        // when
        card = card.copy(password = "1234")

        composeTestRule
            .onNodeWithContentDescription("완료")
            .assertIsEnabled()
    }

    @Test
    fun `카드사_변경되면_카드_수정_버튼_활성화된다`() {
        // when
        card = card.copy(bankType = BankType.KB)

        composeTestRule
            .onNodeWithContentDescription("완료")
            .assertIsEnabled()
    }

    @Test
    fun `카드사를_변경하면_카드에_카드사_이름이_변경되어야한다`() {
        card = card.copy(bankType = BankType.KB)

        composeTestRule
            .onNodeWithTag("PaymentCard_BankName", useUnmergedTree = true)
            .assertTextEquals("국민카드")
    }

    @Test
    fun `카드번호를_입력하면_카드에_카드번호가_변경되어야한다`() {
        // when
        card = card.copy(cardNumber = "1234123412341234")

        composeTestRule
            .onNodeWithTag("PaymentCard_CardNumber", useUnmergedTree = true)
            .assertTextEquals("1234 - 1234 - **** - ****")
    }

    @Test
    fun `만료일을_입력하면_카드에_만료일이_변경되어야한다`() {
        // when
        card = card.copy(expiredDate = "1111")

        composeTestRule
            .onNodeWithTag("PaymentCard_ExpiredDate", useUnmergedTree = true)
            .assertTextEquals("11 / 11")
    }

    @Test
    fun `카드소유자를_입력하면_카드에_카드소유자가_변경되어야한다`() {
        // when
        card = card.copy(ownerName = "카드소유자")

        composeTestRule
            .onNodeWithTag("PaymentCard_OwnerName", useUnmergedTree = true)
            .assertTextEquals("카드소유자")
    }
}