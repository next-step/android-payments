package nextstep.payments.ui.list

import androidx.compose.ui.test.assertContentDescriptionEquals
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.filter
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onLast
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import nextstep.payments.data.model.Card
import org.junit.Rule
import org.junit.Test

class CardListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `카드가_없는_경우_가이드_문구가_보여진다`() {
        val uiState = CardListUiState.Empty

        composeTestRule
            .setContent {
                CardListScreen(
                    cardListUiState = uiState,
                    onAddCardClick = {}
                )
            }

        composeTestRule
            .onNodeWithText("새로운 카드를 등록해주세요")
            .assertIsDisplayed()
    }

    @Test
    fun `카드가_없는_경우_카드_추가_버튼이_클릭_가능하다`() {
        val uiState = CardListUiState.Empty
        var clicked = false

        composeTestRule
            .setContent {
                CardListScreen(
                    cardListUiState = uiState,
                    onAddCardClick = { clicked = true }
                )
            }

        composeTestRule
            .onNodeWithContentDescription("카드 추가")
            .assertIsDisplayed()
    }

    @Test
    fun `카드가_한개만_있는_경우_한개의_완성_카드가_보여진다`() {
        val uiState = CardListUiState.One(
            card = Card(
                number = "1234123412341234",
                expiredDate = "1224",
                ownerName = "홍길동",
                password = "1234123"
            )
        )

        composeTestRule
            .setContent {
                CardListScreen(
                    cardListUiState = uiState,
                    onAddCardClick = { }
                )
            }

        composeTestRule
            .onAllNodesWithContentDescription("완성 카드")
            .assertCountEquals(1)
    }

    @Test
    fun `카드가_한개만_있는_경우_카드목록_마지막에_위치한_카드_추가_버튼이_클릭_가능하다`() {
        val uiState = CardListUiState.One(
            card = Card()
        )
        var clicked = false

        composeTestRule
            .setContent {
                CardListScreen(
                    cardListUiState = uiState,
                    onAddCardClick = { clicked = true }
                )
            }

        val lastInCardList = composeTestRule
            .onNodeWithContentDescription("카드 목록")
            .onChildren()
            .onLast()

        lastInCardList.performClick()

        assert(clicked == true)
        lastInCardList.assertContentDescriptionEquals("카드 추가")
    }

    @Test
    fun `주어진_카드의_갯수만큼_완성_카드가_보여진다`() {
        val uiState = CardListUiState.Many(
            cards = listOf(
                Card(
                    id = 0,
                    number = "1234123412341234"
                ), Card(
                    id = 1,
                    number = "4321432143214321"
                )
            )
        )

        composeTestRule
            .setContent {
                CardListScreen(
                    cardListUiState = uiState,
                    onAddCardClick = { }
                )
            }

        composeTestRule
            .onAllNodesWithContentDescription("미완성 카드")
            .assertCountEquals(0)

        composeTestRule
            .onAllNodesWithContentDescription("완성 카드")
            .assertCountEquals(2)
    }

    @Test
    fun `카드가_여러장인_경우_탑바에_위치한_카드_추가_버튼이_클릭_가능하다`() {
        val uiState = CardListUiState.Many(
            cards = listOf(
                Card(
                    id = 0,
                    number = "1234123412341234"
                ), Card(
                    id = 1,
                    number = "4321432143214321"
                )
            )
        )
        var clicked = false

        composeTestRule
            .setContent {
                CardListScreen(
                    cardListUiState = uiState,
                    onAddCardClick = { clicked = true }
                )
            }

        val buttonInTopBar = composeTestRule
            .onNodeWithContentDescription("탑바")
            .onChildren()
            .filter(hasText("추가"))
            .onFirst()

        buttonInTopBar.performClick()

        assert(clicked == true)
    }

}
