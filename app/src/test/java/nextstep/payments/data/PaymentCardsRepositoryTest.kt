package nextstep.payments.data

import nextstep.payments.model.Card
import org.junit.Assert.*
import org.junit.Test

class PaymentCardsRepositoryTest {

    @Test
    fun `초기에_저장된_카드리스트가_비어있어야_한다`() {

        //given , when
        val repository = PaymentCardsRepository

        //then
        assertTrue(repository.cards.isEmpty())

    }

    @Test
    fun `카드_추가시_카드리스트에_추가되어야_한다`() {

        //given
        val card = Card("", "", "", "")
        val repository = PaymentCardsRepository

        //when
        repository.addCard(card)

        //then
        assertTrue(repository.cards.contains(card))
    }

    @Test
    fun `clear시_추가된_카드가_모두_제거된다`() {

        //given
        val card = Card("", "", "", "")
        val repository = PaymentCardsRepository
        repository.addCard(card)

        //when
        repository.clear()

        //then
        assertTrue(repository.cards.isEmpty())

    }

}