package nextstep.payments.ui.screen.newcard

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.onNodeWithText
import nextstep.payments.BaseComposeTest
import nextstep.payments.ui.screen.newcard.model.CardCompany
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
                onCardClick = {},
                selectedCard = CardCompany.SHINHAN,
                snackbarHostState = SnackbarHostState()
            )
        }
    }

    @Test
    fun 카드번호가_입력되면_처음_8자리는_숫자로_나머지는_별표로_표시된다() {
        cardNumber.value = "1111222233334444"

        composeTestRule.onNodeWithText("1111 - 2222 - **** - ****").assertExists()
    }

    @Test
    fun 카드번호가_16자를_초과하면_초과된_숫자는_무시된다() {
        cardNumber.value = "1111222233334444555555"

        composeTestRule.onNodeWithText("1111 - 2222 - **** - ****").assertExists()
    }

    @Test
    fun 만료일이_입력되면_MM_YY_형식으로_표시된다() {
        expiredData.value = "1234"

        composeTestRule.onNodeWithText("12 / 34").assertExists()
    }

    @Test
    fun 만료일이_4자를_초과해도_초과된_부분은_무시된다() {
        expiredData.value = "1234555666"

        composeTestRule.onNodeWithText("12 / 34").assertExists()
    }
}
