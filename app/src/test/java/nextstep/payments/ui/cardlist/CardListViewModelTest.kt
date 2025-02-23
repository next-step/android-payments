package nextstep.payments.ui.cardlist

import nextstep.payments.data.PaymentCardsRepository
import nextstep.payments.model.BankType
import nextstep.payments.model.Card
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.time.YearMonth

class CardListViewModelTest {

    private lateinit var cardListViewModel: CardListViewModel
    private val repository = PaymentCardsRepository

    @Before
    fun setup() {
        cardListViewModel = CardListViewModel(repository)
    }

    @After
    fun tearDown() {
        repository.clear()
    }

    @Test
    fun `비어있는_카트리스트인경우_fetchCards_호출시_cardListUiState가_Empty이어야_한다`() {
        //given, when
        cardListViewModel.fetchCards()

        //then
        assertEquals(cardListViewModel.cardListUiState.value, CardListUiState.Empty)
    }

    @Test
    fun `한개의_아이템이_카트리스트에_존재하는경우_fetchCards_호출시_cardListUiState가_One이어야_한다`() {

        //given
        val card = Card(
            type = BankType.HANA,
            number = "1",
            expiredDate = YearMonth.of(24, 12),
            ownerName = "1",
            password = "1"
        )

        //when
        repository.addCard(card)
        cardListViewModel.fetchCards()

        //then
        assertEquals(cardListViewModel.cardListUiState.value, CardListUiState.One(card))

    }

    @Test
    fun `여러개의_아이템이_카트리스트에_존재하는경우_fetchCards_호출시_cardListUiState가_Many이어야_한다`() {

        //given
        val cards = listOf(
            Card(
                type = BankType.HANA,
                number = "1",
                expiredDate = YearMonth.of(24, 12),
                ownerName = "1",
                password = "1"
            ),
            Card(
                type = BankType.KB,
                number = "2",
                expiredDate = YearMonth.of(24, 12),
                ownerName = "2",
                password = "2"
            ),
            Card(
                type = BankType.HYUNDAI,
                number = "3",
                expiredDate = YearMonth.of(24, 12),
                ownerName = "3",
                password = "3"
            ),
            Card(
                type = BankType.WOORI,
                number = "4",
                expiredDate = YearMonth.of(24, 12),
                ownerName = "4",
                password = "4"
            )

        )

        //when
        cards.forEach(repository::addCard)
        cardListViewModel.fetchCards()

        //then
        assertEquals(cardListViewModel.cardListUiState.value, CardListUiState.Many(cards))

    }
}