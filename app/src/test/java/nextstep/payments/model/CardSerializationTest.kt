package nextstep.payments.model

import androidx.lifecycle.SavedStateHandle
import junit.framework.TestCase.assertTrue
import nextstep.payments.ext.getSerializable
import nextstep.payments.util.JsonConfig
import org.junit.Test
import java.time.YearMonth

class CardSerializationTest {

    @Test
    fun `직렬화가_올바르게_되어야한다`() {


        //given
        val card = Card(
            type = BankType.HANA,
            number = "1",
            expiredDate = YearMonth.of(24, 12),
            ownerName = "1",
            password = "1"
        )

        val expect =
            "{\"type\":\"HANA\",\"number\":\"1\",\"expiredDate\":\"0024-12\",\"ownerName\":\"1\",\"password\":\"1\"}"


        //when, then
        assertTrue(expect == JsonConfig.json.encodeToString(card))

    }

    @Test
    fun `역직렬화가_올바르게_되어야한다`() {

        //given
        val cardString =
            "{\"type\":\"HANA\",\"number\":\"1\",\"expiredDate\":\"0024-12\",\"ownerName\":\"1\",\"password\":\"1\"}"


        val expect = Card(
            type = BankType.HANA,
            number = "1",
            expiredDate = YearMonth.of(24, 12),
            ownerName = "1",
            password = "1"
        )

        //when,then
        assertTrue(expect == JsonConfig.json.decodeFromString<Card>(cardString))
    }

    @Test
    fun `SavedStateHandle의_확장함수_getSerializable_결과가_올바르게_되어야한다`() {

        //given
        val cardString =
            "{\"type\":\"HANA\",\"number\":\"1\",\"expiredDate\":\"0024-12\",\"ownerName\":\"1\",\"password\":\"1\"}"

        val key = "KEY_CARD_ITEM"

        val savedStateHandle = SavedStateHandle(mapOf(key to cardString))

        val expect = Card(
            type = BankType.HANA,
            number = "1",
            expiredDate = YearMonth.of(24, 12),
            ownerName = "1",
            password = "1"
        )

        //when, then
        assertTrue(expect == savedStateHandle.getSerializable<Card>(key))
    }
}