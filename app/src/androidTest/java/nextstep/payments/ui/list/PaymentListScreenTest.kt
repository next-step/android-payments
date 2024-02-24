package nextstep.payments.ui.list

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import nextstep.payments.ui.domain.model.Card
import org.junit.Rule
import org.junit.Test
import java.time.LocalDate

internal class PaymentListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 카드가_한개도_없다면_새로운_카드를_등록해주세요_가_보여야_한다() {
        // given
        val uiState = PaymentListUiState.Empty

        // when
        composeTestRule.setContent {
            PaymentListScreen(
                uiState = uiState,
                onAddClick = {},
                onAddCardClick = {},
            )
        }

        // then
        composeTestRule
            .onNodeWithText("새로운 카드를 등록해주세요")
            .assertIsDisplayed()
    }

    @Test
    fun 카드_목록에_카드가_한_개_있을_때의_카드_추가_UI는_목록_하단에_노출된다() {
        // given
        val uiState = PaymentListUiState.One(
            card = Card(
                id = "1",
                ownerName = "홍길동",
                cardNumber = "1111222233334444",
                expiredDate = LocalDate.of(2024, 2, 24),
                imageUrl = "",
            )
        )

        // when
        composeTestRule.setContent {
            PaymentListScreen(
                uiState = uiState,
                onAddClick = {},
                onAddCardClick = {},
            )
        }

        // then
        composeTestRule
            .onNodeWithTag("카드 추가")
            .assertIsDisplayed()
    }

    @Test
    fun 카드_목록에_카드가_여러_개_있을_때의_카드_추가_UI는_상단바에_노출된다() {
        // given
        val uiState = PaymentListUiState.Many(
            cards = List(3) {
                Card(
                    id = "1",
                    ownerName = "홍길동",
                    cardNumber = "1111222233334444",
                    expiredDate = LocalDate.of(2024, 2, 24),
                    imageUrl = "",
                )
            }
        )

        // when
        composeTestRule.setContent {
            PaymentListScreen(
                uiState = uiState,
                onAddClick = {},
                onAddCardClick = {},
            )
        }

        // then
        composeTestRule
            .onNodeWithText("추가")
            .assertIsDisplayed()
    }
}
