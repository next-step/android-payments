package nextstep.payments.card

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test
import java.util.Date

class CardsScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val StubCard = Card(
        cardNumber = "1111 - 2222 - 3333 - 4444",
        expireDate = Date(1713625200000), // "2024/04/21",
        ownerName = "Crew",
        cvcNumber = "000",
        password = "1234",
    )

    @Test
    fun `카드가_없는_경우_안내문구와_카드추가_버튼이_나타난다`() {
        // given when
        val viewModel = CardsViewModel(
            cardRepository = object : CardRepository {
                override fun addCard(card: Card) {
                    /* Do nothing */
                }

                override fun getAllCards(): List<Card> {
                    return emptyList()
                }

            }
        )
        composeTestRule.setContent {
            CardsScreen(
                cardsViewModel = viewModel,
                onAddCardClick = {},
                onCardClick = {},
            )
        }

        // then
        composeTestRule
            .onNodeWithText("새로운 카드를 등록해주세요")
            .assertExists()

        composeTestRule
            .onNodeWithTag("AddingCardSlot", useUnmergedTree = true)
            .assertExists()

        composeTestRule
            .onNodeWithTag("PaymentCard", useUnmergedTree = true)
            .assertDoesNotExist()
    }


    @Test
    fun `카드가_한_장_있는_경우_카드와_카드추가_버튼이_나타난다`() {
        // given when
        val viewModel = CardsViewModel(
            cardRepository = object : CardRepository {
                override fun addCard(card: Card) {
                    /* Do nothing */
                }

                override fun getAllCards(): List<Card> {
                    return listOf(StubCard)
                }

            }
        )
        composeTestRule.setContent {
            CardsScreen(
                cardsViewModel = viewModel,
                onAddCardClick = {},
                onCardClick = {},
            )
        }

        // then
        composeTestRule
            .onNodeWithText("새로운 카드를 등록해주세요")
            .assertDoesNotExist()

        composeTestRule
            .onNodeWithTag("AddingCardSlot", useUnmergedTree = true)
            .assertExists()

        composeTestRule
            .onNodeWithTag("PaymentCard", useUnmergedTree = true)
            .assertExists()
    }

    @Test
    fun `카드가_여러_장_있는_경우_카드_목록만이_나타난다`() {
        // given when
        val viewModel = CardsViewModel(
            cardRepository = object : CardRepository {
                override fun addCard(card: Card) {
                    /* Do nothing */
                }

                override fun getAllCards(): List<Card> {
                    return listOf(StubCard, StubCard, StubCard)
                }

            }
        )
        composeTestRule.setContent {
            CardsScreen(
                cardsViewModel = viewModel,
                onAddCardClick = {},
                onCardClick = {},
            )
        }

        // then
        composeTestRule
            .onNodeWithText("새로운 카드를 등록해주세요")
            .assertDoesNotExist()

        composeTestRule
            .onNodeWithTag("AddingCardSlot", useUnmergedTree = true)
            .assertDoesNotExist()

        composeTestRule
            .onAllNodesWithTag("PaymentCard", useUnmergedTree = true)
            .assertCountEquals(3)
    }
}
