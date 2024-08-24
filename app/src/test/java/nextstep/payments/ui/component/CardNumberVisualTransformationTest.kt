package nextstep.payments.ui.component

import androidx.compose.ui.text.AnnotatedString
import junit.framework.TestCase.assertEquals
import org.junit.Test

class CardNumberVisualTransformationTest {
    @Test
    fun 카드번호를_입력하면_카드번호_형식에_맞게_변형되어_노출된다() {
        // given
        val cardNumber = "1234567890123456"
        val expectedFormattedCardNumber = "1234 - 5678 - 9012 - 3456"

        // when
        val actualFormattedCardNumber =
            CardNumberVisualTransformation().filter(AnnotatedString(cardNumber))

        // then
        assertEquals(expectedFormattedCardNumber, actualFormattedCardNumber.text.text)
    }

    @Test
    fun 카드번호_5자리_이상_입력되면_카드번호_형식에_맞게_변형되어_노출된다() {
        // given
        val cardNumber = "12345"
        val expectedFormattedCardNumber = "1234 - 5"

        // when
        val actualFormattedCardNumber =
            CardNumberVisualTransformation().filter((AnnotatedString(cardNumber)))

        // then
        assertEquals(expectedFormattedCardNumber, actualFormattedCardNumber.text.text)
    }

    @Test
    fun 카드번호_4자리만_입력되면_숫자_4자리만_입력된다() {
        // given
        val cardNumber = "1234"
        val expectedFormattedCardNumber = "1234"

        // when
        val actualFormattedCardNumber =
            CardNumberVisualTransformation().filter(AnnotatedString(cardNumber))

        // then
        assertEquals(expectedFormattedCardNumber, actualFormattedCardNumber.text.text)
    }

    // * masking, 12자리 이상 입력되면 8~12자리 *로 마스킹 처리
    @Test
    fun 마스킹_옵션이_있으면_카드번호_8_12자리_로_마스킹_처리된다() {
        // given
        val cardNumber = "1234567890123456"
        val expectedFormattedCardNumber = "1234 - 5678 - **** - ****"
        val validation = CardNumberVisualTransformation(mask = '*')

        // when
        val actualFormattedCardNumber =
            validation.filter(AnnotatedString(cardNumber))

        // then
        assertEquals(expectedFormattedCardNumber, actualFormattedCardNumber.text.text)
    }
}
