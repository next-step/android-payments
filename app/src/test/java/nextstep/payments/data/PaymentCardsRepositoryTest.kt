package nextstep.payments.data

import nextstep.payments.model.BankType
import nextstep.payments.model.Card
import org.junit.Assert.*
import org.junit.Test
import java.time.YearMonth

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
        val card = Card(
            type = BankType.HANA,
            number = "1",
            expiredDate = YearMonth.of(24, 12),
            ownerName = "1",
            password = "1"
        )
        val repository = PaymentCardsRepository

        //when
        repository.addCard(card)

        //then
        assertTrue(repository.cards.contains(card))
    }

    @Test
    fun `clear시_추가된_카드가_모두_제거된다`() {

        //given
        val card = Card(
            type = BankType.KAKAO,
            number = "2",
            expiredDate = YearMonth.of(24,12),
            ownerName = "2",
            password = "2"
        )
        val repository = PaymentCardsRepository
        repository.addCard(card)

        //when
        repository.clear()

        //then
        assertTrue(repository.cards.isEmpty())

    }

}