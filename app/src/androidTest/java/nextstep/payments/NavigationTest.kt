package nextstep.payments

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.NavHostController
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.rememberNavController
import nextstep.payments.ui.AppNavHost
import nextstep.payments.ui.navigation.NavigationItem
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NavigationTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    private lateinit var navController: NavHostController

    @Before
    fun setupAppNavHost() {
        composeTestRule.setContent {
            navController = rememberNavController()
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            AppNavHost(navHostController = navController)
        }
    }

    @Test
    fun 시작화면은_카드리스트_화면이다() {
        val actual = navController.currentBackStackEntry?.destination?.route
        assert(actual == NavigationItem.PaymentCards.route)
    }

    @Test
    fun 카드리스트에서_추가버튼_클릭시_카드추가_화면으로_이동한다() {
        // given
        시작화면은_카드리스트_화면이다()
        // when
        composeTestRule
            .onNodeWithContentDescription("추가")
            .performClick()
        val current = navController.currentBackStackEntry?.destination?.route

        // then
        composeTestRule
            .onNodeWithText("카드 추가")
            .assertExists()
        assert(current == NavigationItem.AddCard.route)
    }

    @Test
    fun 카드추가후_카드리스트_화면에서_추가된_카드가_보여진다() {

        // given
        카드리스트에서_추가버튼_클릭시_카드추가_화면으로_이동한다()

        // when
        composeTestRule
            .onNodeWithText("카드 소유자 이름(선택)")
            .performTextInput("카드소유자")
        composeTestRule
            .onNodeWithContentDescription("카드 추가")
            .performClick()

        // then
        composeTestRule.runOnIdle {
            val current = navController.currentBackStackEntry?.destination?.route
            assert(current == NavigationItem.PaymentCards.route)
        }
        composeTestRule
            .onNodeWithText("카드소유자")
            .assertExists()
    }
}
