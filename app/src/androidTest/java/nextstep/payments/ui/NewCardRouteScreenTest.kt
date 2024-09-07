package nextstep.payments.ui

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import nextstep.payments.screen.model.BankTypeUiModel
import nextstep.payments.screen.newcard.NewCardRouteScreen
import nextstep.payments.screen.newcard.NewCardViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class NewCardRouteScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private lateinit var viewModel: NewCardViewModel

    @Before
    fun setUp(){
        viewModel = NewCardViewModel()
    }

    @Test
    fun 카드가_추가되면_화면_이동_로직이_실행된다() {
        var isNavigated = false

        //GIVEN
        composeTestRule.setContent {
            NewCardRouteScreen(
                navigateToCardList = {
                    isNavigated  = true
                },
                viewModel = viewModel
            )
        }
        viewModel.setBankType(BankTypeUiModel.BC)
        viewModel.setCardNumber("1234123412341234")
        viewModel.setExpiredDate("1228")
        viewModel.setPassword("1234")

        //WHEN
        composeTestRule.onNodeWithTag("saveButton").performClick()

        composeTestRule.waitForIdle()

        //THEN
        assert(isNavigated)
    }


}

