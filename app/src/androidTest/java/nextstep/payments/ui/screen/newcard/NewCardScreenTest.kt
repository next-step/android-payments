package nextstep.payments.ui.screen.newcard

import android.content.Context
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.test.core.app.ApplicationProvider
import nextstep.payments.BaseComposeTest
import nextstep.payments.R
import nextstep.payments.ui.screen.newcard.model.BankTypeModel
import nextstep.payments.ui.utils.chunkedCardNumber
import nextstep.payments.ui.utils.toFormattedExpirationDate
import nextstep.payments.utils.assertBackgroundColor
import org.junit.Before
import org.junit.Test

internal class NewCardScreenTest : BaseComposeTest() {

    private val cardNumber = mutableStateOf("")
    private val expiredData = mutableStateOf("")
    private val cardOwnerName = mutableStateOf("")
    private val password = mutableStateOf("")
    private val bankType = mutableStateOf(BankTypeModel.NOT_SELECTED)
    private val context = ApplicationProvider.getApplicationContext<Context>()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            NewCardScreen(
                cardNumber = cardNumber.value,
                expiredDate = expiredData.value,
                ownerName = cardOwnerName.value,
                password = password.value,
                setCardNumber = { cardNumber.value = it },
                setExpiredDate = { expiredData.value = it },
                onBackClick = {},
                onSaveClick = {},
                setOwnerName = {},
                setPassword = {},
                onCardClick = {},
                bankType = bankType.value,
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

    @Test
    fun 카드_은행선택이_달라지면_현재_카드_배경색이_달라져야한다() {
        val shinhanBank = BankTypeModel.SHINHAN
        val kakaoBank = BankTypeModel.KAKAO
        bankType.value = kakaoBank
        cardOwnerName.value = "이지훈"
        cardNumber.value = "1111222233334444"
        expiredData.value = "2233"
        val kakaoCardDescription = context.getString(
            R.string.payment_card_content_description,
            kakaoBank.companyName,
            cardOwnerName.value,
            cardNumber.value.chunkedCardNumber(),
            expiredData.value.toFormattedExpirationDate(
                maxLength = 4,
                separator = "/"
            )
        )

        composeTestRule
            .onNodeWithContentDescription(kakaoCardDescription)
            .assertBackgroundColor(Color(0xFFFFE600))

        val shinhanCardDescription = context.getString(
            R.string.payment_card_content_description,
            shinhanBank.companyName,
            cardOwnerName.value,
            cardNumber.value.chunkedCardNumber(),
            expiredData.value.toFormattedExpirationDate(
                maxLength = 4,
                separator = "/"
            )
        )

        bankType.value = shinhanBank

        composeTestRule
            .onNodeWithContentDescription(shinhanCardDescription)
            .assertBackgroundColor(Color(0xFF0046FF))
    }
}
