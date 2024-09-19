package nextstep.payments.newcard.component.newcard.navigation

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import nextstep.payments.ui.AppNavHost
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NewCardNavigationTest {

    @get:Rule
    val composeRule = createComposeRule()
    private lateinit var navController: TestNavHostController

    @Before
    fun setup() {
        composeRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            AppNavHost(navController = navController)
        }.also { composeRule.onNodeWithText("추가").performClick() }
    }

    @Test
    fun 뒤로가기_버튼을_클릭할_경우_이전뷰로_이동한다() {
        // given:
        // when:
        composeRule.onNodeWithContentDescription("뒤로 가기").performClick()
        val actual = navController.currentBackStackEntry?.destination?.route

        // then:
        val expected = "CardListScreen"

        assert(actual == expected)
    }

    @Test
    fun 체크_버튼을_클릭할_경우_카드_목록_뷰로_이동한다() {
        // given:
        composeRule.onNodeWithText("카드 번호").performTextInput("1234123412341234")
        composeRule.onNodeWithText("만료일").performTextInput("1231")
        composeRule.onNodeWithText("비밀번호").performTextInput("1234")

        // when:
        composeRule.onNodeWithContentDescription("완료").performClick()
        val actual = navController.currentBackStackEntry?.destination?.route

        // then:
        val expected = "CardListScreen"
        assert(actual == expected)
    }
}
