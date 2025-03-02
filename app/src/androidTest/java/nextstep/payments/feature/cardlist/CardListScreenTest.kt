package nextstep.payments.feature.cardlist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import nextstep.payments.model.Card
import nextstep.payments.ui.theme.PaymentsTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CardListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private var cardUiState by mutableStateOf<CardUiState>(CardUiState.Empty)

    @Before
    fun setUp() {
        composeTestRule.setContent {
            PaymentsTheme {
                CardListScreen(
                    onAddClick = {},
                    cardUiState = cardUiState
                )
            }
        }
    }

    @Test
    fun 카드_목록이_비어있을_때_새로운_카드를_등록해주세요_안내가_노출된다() {
        cardUiState = CardUiState.Empty

        composeTestRule.onNodeWithText("새로운 카드를 등록해주세요").assertIsDisplayed()
    }

    @Test
    fun 카드_목록에_한_개의_카드가_있을_때_하단에_카드_추가_UI가_노출된다() {
        cardUiState = CardUiState.One(Card.mock)

        composeTestRule.onNodeWithTag("AddNewCardImage").assertIsDisplayed()
    }

    @Test
    fun 카드_목록에_여러_개의_카드가_있을_때_상단바에_카드_추가_UI가_노출된다() {
        cardUiState = CardUiState.Many(listOf(Card.mock, Card.mock, Card.mock))
        composeTestRule.onNodeWithTag("AddNewCardButton").assertIsDisplayed()
    }

}
