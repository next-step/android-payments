package nextstep.payments.data

import nextstep.payments.model.BankType
import nextstep.payments.model.Card
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.time.YearMonth

class PaymentCardsRepositoryTest {

    private val paymentRepository = PaymentCardsRepository

    @Before
    fun setUp() {
        paymentRepository.clear()
    }

    @Test
    fun `초기에_저장된_카드리스트가_비어있어야_한다`() {

        //then
        assertTrue(paymentRepository.cards.isEmpty())
    }

    @Test
    fun `clear시_추가된_카드가_모두_제거된다`() {

        //given
        val card = Card(
            type = BankType.KAKAO,
            number = "2",
            expiredDate = YearMonth.of(24, 12),
            ownerName = "2",
            password = "2"
        )
        paymentRepository.upsertCard(card)

        //when
        paymentRepository.clear()

        //then
        assertTrue(paymentRepository.cards.isEmpty())
    }


    @Test
    fun `새로운_카드를_추가시_리턴값이_true이어야_한다`() {
        //given
        val card = Card(
            type = BankType.HANA,
            number = "1",
            expiredDate = YearMonth.of(24, 12),
            ownerName = "1",
            password = "1"
        )

        //when , then
        assertTrue(paymentRepository.upsertCard(card))
    }


    @Test
    fun `동일한_카드를_추가시_리턴값이_false이어야_한다`() {

        //given
        val card = Card(
            type = BankType.HANA,
            number = "1",
            expiredDate = YearMonth.of(24, 12),
            ownerName = "1",
            password = "1"
        )

        //when
        paymentRepository.upsertCard(card)

        //then
        assertFalse(paymentRepository.upsertCard(card))
    }
}