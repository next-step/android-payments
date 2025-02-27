package nextstep.payments

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.lifecycle.ViewModelProvider
import nextstep.payments.data.PaymentCardsRepository
import nextstep.payments.model.BankType
import nextstep.payments.model.Card
import nextstep.payments.ui.cardlist.CardListViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.time.YearMonth

class RouteActivityTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        PaymentCardsRepository.clear()
    }

    @Test
    fun `카드가_비어있는_경우_카드추가_버튼을_클릭하면_새로운_카드를_추가하는_화면으로_이동해야_한다`() {
        //when
        composeTestRule.onNodeWithContentDescription("add_card_icon").performClick()

        composeTestRule.waitForIdle()

        //then
        composeTestRule.onNodeWithContentDescription("BankSelectBottomSheet").isDisplayed()
    }

    @Test
    fun `카드가_한개만_있는_경우_카드추가_버튼을_클릭하면_새로운_카드를_추가하는_화면으로_이동해야_한다`() {
        //given
        val card = Card(
            type = BankType.BC,
            number = "2345234523452345",
            expiredDate = YearMonth.of(28, 12),
            ownerName = "",
            password = ""
        )
        PaymentCardsRepository.upsertCard(card)

        val cardListViewModel =
            ViewModelProvider(composeTestRule.activity)[CardListViewModel::class.java]

        cardListViewModel.fetchCards()
        composeTestRule.waitForIdle()


        //when
        composeTestRule.onNodeWithContentDescription("add_card_icon").performClick()

        composeTestRule.waitForIdle()

        //then
        composeTestRule.onNodeWithContentDescription("BankSelectBottomSheet").isDisplayed()
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

        val cardListViewModel =
            ViewModelProvider(composeTestRule.activity)[CardListViewModel::class.java]

        cardListViewModel.fetchCards()
        composeTestRule.waitForIdle()

        //when
        composeTestRule.onNodeWithText("추가").performClick()

        composeTestRule.waitForIdle()

        //then
        composeTestRule.onNodeWithContentDescription("BankSelectBottomSheet").isDisplayed()
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

        val cardListViewModel =
            ViewModelProvider(composeTestRule.activity)[CardListViewModel::class.java]

        cardListViewModel.fetchCards()
        composeTestRule.waitForIdle()


        //when
        composeTestRule
            .onNodeWithText("비씨카드")
            .performClick()

        composeTestRule.waitForIdle()

        //then
        composeTestRule
            .onNodeWithText("카드 수정")
            .assertIsDisplayed()
    }
}