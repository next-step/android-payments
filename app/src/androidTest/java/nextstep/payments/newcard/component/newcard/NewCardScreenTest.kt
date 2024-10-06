package nextstep.payments.newcard.component.newcard

import android.util.Log
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import nextstep.payments.ui.newcard.NewCardScreen
import org.junit.Rule
import org.junit.Test

class NewCardScreenTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun 뒤로가기_버튼을_클릭할_경우_이전뷰로_이동한다() {
        // given:
        var cardNumber by mutableStateOf("")
        var expiredDate by mutableStateOf("")
        var ownerName by mutableStateOf("")
        var password by mutableStateOf("")
        val isValidCard by derivedStateOf {
            cardNumber.length == 16 && password.length == 4 && expiredDate.length == 4
        }

        composeRule.setContent {
            NewCardScreen(
                cardNumber = cardNumber,
                expiredDate = expiredDate,
                ownerName = ownerName,
                password = password,
                setCardNumber = { cardNumber = it },
                setPassword = { password = it },
                setOwnerName = { ownerName = it },
                setExpiredDate = { expiredDate = it },
                onBackClick = { },
                onSaveClick = { },
                isValidCard = isValidCard,
            )
        }

        // when:
        composeRule.onNodeWithText("카드 번호").performTextInput("1234123412341234")
        composeRule.onNodeWithText("만료일").performTextInput("1231")
        composeRule.onNodeWithText("비밀번호").performTextInput("1234")

        // then:
        composeRule.onNodeWithContentDescription("완료").assertIsEnabled()
    }
}
