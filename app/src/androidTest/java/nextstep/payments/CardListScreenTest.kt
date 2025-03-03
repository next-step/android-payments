package nextstep.payments

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasParent
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import nextstep.payments.list.CardListScreen
import nextstep.payments.list.CardListState
import nextstep.payments.model.CreditCard
import nextstep.payments.ui.theme.PaymentsTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CardListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private var state by mutableStateOf<CardListState>(CardListState.Empty)

    @Before
    fun setUp() {
        composeTestRule.setContent {
            PaymentsTheme {
                CardListScreen(
                    state = state,
                    onAddClick = {}
                )
            }
        }
    }

    @Test
    fun 카드_목록이_비어있을_때에는_새로운_카드를_등록해주세요_안내가_노출되어야_한다() {
        // when
        state = CardListState.Empty

        // then
        composeTestRule.onNodeWithText("새로운 카드를 등록해주세요").assertExists()
    }

    @Test
    fun 카드_목록에_카드가_한_개_있을_때의_카드_추가_UI는_목록_하단에_노출된다() {
        // when
        state = CardListState.Single(CreditCard.emptyCard)

        // then
        val columnNode = composeTestRule.onNodeWithTag("SingleCardContentColumn")
        val lastIndex = columnNode.fetchSemanticsNode().children.lastIndex
        columnNode.onChildAt(lastIndex).assert(hasTestTag("EmptyPaymentCard"))
    }

    @Test
    fun 카드_목록에_카드가_여러_개_있을_때의_카드_추가_UI는_상단바에_노출된다() {
        // when
        state = CardListState.Multiple(listOf(CreditCard.emptyCard, CreditCard.emptyCard))

        // then
        composeTestRule.onNodeWithTag("Add")
            .assert(hasParent(hasTestTag("CardListTopBar")))
            .assertExists()
    }
}