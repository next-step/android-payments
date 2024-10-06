package nextstep.payments.newcard.component.cardlist.navigation

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import nextstep.payments.ui.AppNavHost
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CardListNavigationTest {

    @get:Rule
    val composeRule = createComposeRule()
    private lateinit var navController: TestNavHostController

    @Before
    fun setup() {
        composeRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            AppNavHost(navController = navController)
        }
    }

    @Test
    fun 시작_화면은_카드_목록_뷰이다() {
        // given:
        // when:
        val actual = navController.currentBackStackEntry?.destination?.route

        // then:
        val expected = "CardListScreen"

        assert(actual == expected)
    }

    @Test
    fun 추가를_클릭할_경우_카드_추가_뷰로_이동한다() {
        // given:
        // when:
        composeRule.onNodeWithText("추가").performClick()
        val actual = navController.currentBackStackEntry?.destination?.route

        // then:
        val expected = "NewCardScreen"
        assert(actual == expected)
    }
}
