package nextstep.payments.newcard.component.cardlist

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import nextstep.payments.data.CardState
import nextstep.payments.data.CardState.EmptyCard
import nextstep.payments.ui.cardlist.CardListScreen
import org.junit.Rule
import org.junit.Test

class CardListScreenTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun `등록된_카드가_없을_때_카드등록요청_문구가_노출된다`() {
        // given:
        val registeredCards: ImmutableList<CardState> = persistentListOf(EmptyCard)

        // when:
        composeRule.setContent {
            CardListScreen(
                isEmptyOfRegisteredCards = registeredCards.first() is EmptyCard,
                cards = registeredCards,
                onAddCardClick = { },
            )
        }

        // then:
        composeRule.onNodeWithText("새로운 카드를 등록해주세요").assertIsDisplayed()
    }

    @Test
    fun `등록된_카드가_없을_때_빈_카드가_노출된다`() {
        // given:
        val registeredCards: ImmutableList<CardState> = persistentListOf(EmptyCard)

        // when:
        composeRule.setContent {
            CardListScreen(
                isEmptyOfRegisteredCards = registeredCards.first() is EmptyCard,
                cards = registeredCards,
                onAddCardClick = { },
            )
        }

        // then:
        composeRule.onNodeWithContentDescription("CardListEmptyCard").assertIsDisplayed()
    }

    @Test
    fun `등록된_카드가_1개일때_등록된_카드_1개가_노출된다`() {
        // given:
        val registeredCards: ImmutableList<CardState> = persistentListOf(
            CardState.Card(
                cardId = 0,
                cardNumber = "1234123412341234",
                expiredDate = "1221",
                ownerName = "세훈",
                password = "1234",
            ),
            EmptyCard,
        )

        // when:
        composeRule.setContent {
            CardListScreen(
                isEmptyOfRegisteredCards = registeredCards.first() is EmptyCard,
                cards = registeredCards,
                onAddCardClick = { },
            )
        }

        // then:
        composeRule.onNodeWithContentDescription("RegisteredPaymentCard").assertIsDisplayed()
        composeRule.onAllNodesWithContentDescription("RegisteredPaymentCard").assertCountEquals(1)
    }

    @Test
    fun `등록된_카드가_4개_미만일때_빈_카드가_최하단에_위치한다`() {
        // given:
        val registeredCards: ImmutableList<CardState> = (List(3) {
            CardState.Card(
                cardId = it.toLong(),
                cardNumber = "1234123412341234",
                expiredDate = "122$it",
                ownerName = "세훈$it",
                password = "1234",
            )
        } + EmptyCard).toImmutableList()


        // when:
        composeRule.setContent {
            CardListScreen(
                isEmptyOfRegisteredCards = registeredCards.first() is EmptyCard,
                cards = registeredCards,
                onAddCardClick = { },
            )
        }

        // then:
        val emptyCard = composeRule.onNodeWithContentDescription("CardListEmptyCard")
        val cards = composeRule.onNodeWithContentDescription("CardListLazyColumn")
            .fetchSemanticsNode().children

        emptyCard.assertExists()
        assert(emptyCard.fetchSemanticsNode().id == cards.last().id)
    }

    @Test
    fun `등록된_카드가_4개_이상일_때_빈_카드가_사라진다`() {
        // given:
        val registeredCards: ImmutableList<CardState> = List(4) {
            CardState.Card(
                cardId = it.toLong(),
                cardNumber = "1234123412341234",
                expiredDate = "122$it",
                ownerName = "세훈$it",
                password = "1234",
            )
        }.toImmutableList()

        // when:
        composeRule.setContent {
            CardListScreen(
                isEmptyOfRegisteredCards = registeredCards.first() is EmptyCard,
                cards = registeredCards,
                onAddCardClick = { },
            )
        }

        // then:
        val emptyCard = composeRule.onNodeWithContentDescription("CardListEmptyCard")
        val cards = composeRule.onNodeWithContentDescription("CardListLazyColumn")
            .fetchSemanticsNode().children

        assert(cards.size >= 4)
        emptyCard.assertDoesNotExist()
    }

    @Test
    fun `등록된_카드가_4개_이상일_때_우측_상단에_추가버튼이_노출된다`() {
        // given:

        // when:

        // then:
    }
}
