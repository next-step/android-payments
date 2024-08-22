package nextstep.payments.ui.screen.newcard

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.onNodeWithText
import nextstep.payments.BaseComposeTest
import org.junit.Before
import org.junit.Test

internal class NewCardScreenTest : BaseComposeTest() {

    private val cardNumber = mutableStateOf("")
    private val expiredData = mutableStateOf("")

    @Before
    fun setUp() {
        composeTestRule.setContent {
            NewCardScreen(
                cardNumber = cardNumber.value,
                expiredDate = expiredData.value,
                ownerName = "",
                password = "",
                setCardNumber = { cardNumber.value = it },
                setExpiredDate = { expiredData.value = it },
                onBackClick = {},
                onSaveClick = {},
                setOwnerName = {},
                setPassword = {},
            )
        }
    }

    @Test
    fun 카드번호가_입력되면_카드번호_8자리까지는_숫자로_그이후는_별표_형식으로_표시된다() {
        cardNumber.value = "1111222233334444"

        composeTestRule.onNodeWithText("1111 - 2222 - **** - ****").assertExists()
    }

    @Test
    fun 카드번호가_16자가_초과한_입력은_무시하고_먼저_입력된을_기준으로_출력된다() {
        cardNumber.value = "1111222233334444555555"

        composeTestRule.onNodeWithText("1111 - 2222 - **** - ****").assertExists()
    }


    @Test
    fun 만료일자가_입력되면_MM_YY_형식으로_표시된다() {
        expiredData.value = "1234"

        composeTestRule.onNodeWithText("12 / 34").assertExists()
    }

    @Test
    fun 만료일자가_초과해도_무시하고_먼저_입력된을_기준으로_출력된다() {
        expiredData.value = "1234555666"

        composeTestRule.onNodeWithText("12 / 34").assertExists()
    }
}
