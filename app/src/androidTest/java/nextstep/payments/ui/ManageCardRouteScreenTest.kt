package nextstep.payments.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.lifecycle.SavedStateHandle
import nextstep.payments.screen.model.BankTypeUiModel
import nextstep.payments.screen.cardmanage.ManageCardRouteScreen
import nextstep.payments.screen.cardmanage.ManageCardViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class ManageCardRouteScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    private lateinit var viewModel: ManageCardViewModel

    @Before
    fun setUp(){
        viewModel = ManageCardViewModel(
            savedStateHandle = SavedStateHandle()
        )
    }

    @Test
    fun 카드가_추가되면_화면_이동_로직이_실행된다() {
        var isNavigated = false

        //GIVEN
        composeTestRule.setContent {
            ManageCardRouteScreen(
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

    @Test
    fun 새_카드_추가_화면_진입_시_카드사_선택_바텀_시트가_나타난다(){
        //GIVEN
        composeTestRule.setContent {
            ManageCardRouteScreen(
                navigateToCardList = { },
                viewModel = viewModel
            )
        }

        //THEN
        composeTestRule.onNodeWithTag("BankSelectBottomSheet")
            .assertIsDisplayed()
    }

    @Test
    fun 카드사_선택을_하지_않고_뒤로_가기_시_카드_추가_화면에서_빠져나온다() {
        //GIVEN
        composeTestRule.setContent {
            ManageCardRouteScreen(
                modifier = Modifier.testTag("ManageCardRouteScreen"),
                navigateToCardList = { },
                viewModel = viewModel
            )
        }

        //WHEN
        composeTestRule.runOnUiThread {
            composeTestRule.activity.onBackPressedDispatcher.onBackPressed()
        }

        composeTestRule.waitForIdle()

        //THEN
        composeTestRule.onNodeWithTag("ManageCardRouteScreen")
            .assertDoesNotExist()
    }

    @Test
    fun 카드사를_선택하면_바텀시트가_내려가고_카드사가_선택되어있다() {
        //GIVEN
        composeTestRule.setContent {
            ManageCardRouteScreen(
                modifier = Modifier.testTag("ManageCardRouteScreen"),
                navigateToCardList = { },
                viewModel = viewModel
            )
        }

        //WHEN
        composeTestRule.onNodeWithTag(BankTypeUiModel.BC.name).performClick()

        composeTestRule.waitForIdle()

        //THEN
        composeTestRule.onNodeWithTag("BankSelectBottomSheet")
            .assertIsNotDisplayed()
        assert(viewModel.bankType.value == BankTypeUiModel.BC)
    }
}

