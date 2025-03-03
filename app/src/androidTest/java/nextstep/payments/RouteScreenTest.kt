package nextstep.payments

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import nextstep.payments.data.PaymentCardsRepository
import nextstep.payments.model.BankType
import nextstep.payments.model.Card
import nextstep.payments.navigation.rememberNavigator
import nextstep.payments.ui.cardlist.CardListViewModel
import nextstep.payments.ui.cardlist.navigation.CardList
import nextstep.payments.ui.main.MainScreen
import nextstep.payments.ui.newcard.navigation.NewCard
import nextstep.payments.ui.updatecard.navigation.UpdateCard
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.time.YearMonth

class RouteScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    private lateinit var navController: TestNavHostController

    @Before
    fun setUp() {
        PaymentCardsRepository.clear()
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            MainScreen(navigator = rememberNavigator(navController))
        }
    }

    @Test
    fun `시작경로가_올바르게_나와야_한다`() {
        assertEquals(navController.currentDestination?.hasRoute<CardList>(), true)
    }

    @Test
    fun `카드가_비어있는_경우_카드추가_버튼을_클릭하면_새로운_카드를_추가하는_화면으로_이동해야_한다`() {

        //when
        composeTestRule.onNodeWithContentDescription("add_card_icon").performClick()

        //then
        assertEquals(navController.currentDestination?.hasRoute<NewCard>(), true)
    }

    @Test
    fun `카드가_한개만_있는_경우_카드추가_버튼을_클릭하면_새로운_카드를_추가하는_화면으로_이동해야_한다`() {
        // given
        val card = Card(
            type = BankType.BC,
            number = "2345234523452345",
            expiredDate = YearMonth.of(28, 12),
            ownerName = "",
            password = ""
        )
        PaymentCardsRepository.upsertCard(card)

        navController.currentBackStackEntry?.let {
            ViewModelProvider(it)[CardListViewModel::class.java].fetchCards()
        }
        composeTestRule.waitForIdle()

        //when
        composeTestRule.onNodeWithContentDescription("add_card_icon").performClick()

        //then
        assertEquals(navController.currentDestination?.hasRoute<NewCard>(), true)
    }


    @Test
    fun `카드가_여러개인_경우_추가_텍스트를_클릭하면_새로운_카드를_추가하는_화면으로_이동해야_한다`() {
        //given
        val card = listOf(
            Card(
                type = BankType.BC,
                number = "2345234523452345",
                expiredDate = YearMonth.of(28, 12),
                ownerName = "1234",
                password = "1234"
            ),
            Card(
                type = BankType.KB,
                number = "1234123412341234",
                expiredDate = YearMonth.of(28, 12),
                ownerName = "2345",
                password = "2345"
            )
        )
        card.forEach(PaymentCardsRepository::upsertCard)

        navController.currentBackStackEntry?.let {
            ViewModelProvider(it)[CardListViewModel::class.java].fetchCards()
        }
        composeTestRule.waitForIdle()

        //when
        composeTestRule.onNodeWithText("추가").performClick()


        //then
        assertEquals(navController.currentDestination?.hasRoute<NewCard>(), true)
    }

    @Test
    fun `카드가_있는경우_클릭시_수정화면으로_이동해야_한다`() {
        //given
        val card = Card(
            type = BankType.BC,
            number = "2345234523452345",
            expiredDate = YearMonth.of(28, 12),
            ownerName = "",
            password = ""
        )
        PaymentCardsRepository.upsertCard(card)
        navController.currentBackStackEntry?.let {
            ViewModelProvider(it)[CardListViewModel::class.java].fetchCards()
        }
        composeTestRule.waitForIdle()

        //when
        composeTestRule
            .onNodeWithText("비씨카드")
            .performClick()

        //then
        assertEquals(navController.currentDestination?.hasRoute<UpdateCard>(), true)
    }
}