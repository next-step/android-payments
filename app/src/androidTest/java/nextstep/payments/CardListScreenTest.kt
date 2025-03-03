package nextstep.payments

import androidx.compose.ui.test.filter
import androidx.compose.ui.test.hasAnySibling
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import nextstep.payments.data.model.Card
import nextstep.payments.data.repository.PaymentCardsRepository
import nextstep.payments.ui.screen.CardListScreen
import org.junit.Rule
import org.junit.Test

class CardListScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    fun registerCards(vararg cards: Card) {
        PaymentCardsRepository.clearCards()

        cards.forEach { PaymentCardsRepository.addCard(it) }

        composeTestRule.setContent {
            CardListScreen(
                navigateToNewCard = {},
            )
        }
    }

    @Test
    fun 등록된_카드가_없을시_카드_등록_문구가_화면에_노출된다() {
        registerCards()

        // 등록된 카드가 없을 때 카드 등록 문구가 화면에 노출되는지 확인
        composeTestRule.onNodeWithText(REGISTER_NEW_CARD_TEXT)
            .assertExists()
    }

    @Test
    fun 등록된_카드가_없을시_카드_등록_버튼이_화면에_노출된다() {
        registerCards()

        // 등록된 카드가 없을 때 카드 등록 버튼이 화면에 노출되는지 확인
        composeTestRule.onNodeWithContentDescription(REGISTER_CARD_BUTTON_CONTAINER_DESCRIPTION)
            .assertExists()
    }

    @Test
    fun 등록된_카드가_하나_있을시_카드가_화면에_노출된다() {
        registerCards(
            Card(
                cardNumber = "1234567812345678",
                expiredDate = "1223",
                ownerName = "홍길동",
                password = "1234",
            )
        )

        // 등록된 카드가 있을 때 카드가 화면에 노출되는지 확인
        composeTestRule.onAllNodesWithText("홍길동")
            .filter(hasAnySibling(hasText("1234")))
            .filter(hasAnySibling(hasText("12 / 23")))
            .onFirst()
            .assertExists()
    }

    @Test
    fun 등록된_카드가_하나일시_카드_등록_버튼이_화면에_노출된다() {
        registerCards(
            Card(
                cardNumber = "1234567812345678",
                expiredDate = "1223",
                ownerName = "홍길동",
                password = "1234",
            )
        )

        // 등록된 카드가 하나일 때 카드 등록 버튼이 화면에 노출되는지 확인
        composeTestRule.onNodeWithContentDescription(REGISTER_CARD_BUTTON_CONTAINER_DESCRIPTION)
            .assertExists()
    }

    @Test
    fun 등록된_카드가_2개_이상일시_카드_리스트가_화면에_노출된다() {
        registerCards(
            Card(
                cardNumber = "1234567812345678",
                expiredDate = "1223",
                ownerName = "홍길동",
                password = "1234",
            ),
            Card(
                cardNumber = "1234567812345678",
                expiredDate = "1223",
                ownerName = "홀리물리",
                password = "1234",
            )
        )

        // 등록된 카드가 있을 때 카드가 화면에 노출되는지 확인
        composeTestRule.onAllNodesWithText("홍길동")
            .filter(hasAnySibling(hasText("1234")))
            .filter(hasAnySibling(hasText("12 / 23")))
            .onFirst()
            .assertExists()

        composeTestRule.onAllNodesWithText("홀리물리")
            .filter(hasAnySibling(hasText("1234")))
            .filter(hasAnySibling(hasText("12 / 23")))
            .onFirst()
            .assertExists()
    }

    @Test
    fun 등록된_카드가_2개_이상일시_앱바에_추가_버튼이_노출된다() {
        registerCards(
            Card(
                cardNumber = "1234567812345678",
                expiredDate = "1223",
                ownerName = "홍길동",
                password = "1234",
            ),
            Card(
                cardNumber = "1234567812345678",
                expiredDate = "1223",
                ownerName = "홀리물리",
                password = "1234",
            )
        )

        // 등록된 카드가 2개 이상일 때 앱바에 추가 버튼이 노출되는지 확인
        composeTestRule.onNodeWithText(ADD_CARD_APP_BAR_TEXT)
            .assertExists()
    }

    @Test
    fun 등록된_카드가_2개_이상일시_카드_등록_버튼이_노출되지_않는다() {
        registerCards(
            Card(
                cardNumber = "1234567812345678",
                expiredDate = "1223",
                ownerName = "홍길동",
                password = "1234",
            ),
            Card(
                cardNumber = "1234567812345678",
                expiredDate = "1223",
                ownerName = "홀리물리",
                password = "1234",
            )
        )

        // 등록된 카드가 2개 이상일 때 카드 등록 버튼이 노출되지 않는지 확인
        composeTestRule.onNodeWithContentDescription(REGISTER_CARD_BUTTON_CONTAINER_DESCRIPTION)
            .assertDoesNotExist()
    }

    companion object {
        const val REGISTER_NEW_CARD_TEXT = "새로운 카드를 등록해주세요"
        const val REGISTER_CARD_BUTTON_CONTAINER_DESCRIPTION = "registerCardContainer"
        const val ADD_CARD_APP_BAR_TEXT = "추가"
    }
}