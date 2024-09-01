package nextstep.payments.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import nextstep.payments.screen.newcard.NewCardEvent
import nextstep.payments.screen.newcard.NewCardScreen
import org.junit.Rule
import org.junit.Test

internal class NewCardScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 카드가_추가되면_화면_이동_로직이_실행된다() {
        var newCardEvent by mutableStateOf(NewCardEvent.Pending)
        var isNavigated = false

        //GIVEN
        composeTestRule.setContent {
            NewCardScreen(
                cardNumber = "",
                expiredDate = "",
                ownerName = "",
                password = "",
                cardAdded = newCardEvent,
                setCardNumber = {},
                setExpiredDate = {},
                setOwnerName = {},
                setPassword = {},
                onBackClick = {},
                onSaveClick = {
                    newCardEvent = NewCardEvent.Success
                },
                navigateToCardList = {
                    isNavigated = true
                }
            )
        }

        //WHEN
        composeTestRule.onNodeWithTag("saveButton").performClick()

        composeTestRule.waitForIdle()

        //THEN
        assert(isNavigated)
    }
}

