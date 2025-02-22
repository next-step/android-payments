package nextstep.payments.ui.cardlist

import nextstep.payments.data.PaymentCardsRepository
import nextstep.payments.model.Card
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

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
            number = "",
            expiredDate = "",
            ownerName = "",
            password = ""
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
                number = "1",
                expiredDate = "1",
                ownerName = "1",
                password = "1"
            ),
            Card(
                number = "2",
                expiredDate = "2",
                ownerName = "2",
                password = "2"
            ),
            Card(
                number = "3",
                expiredDate = "3",
                ownerName = "3",
                password = "3"
            ),
            Card(
                number = "4",
                expiredDate = "4",
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