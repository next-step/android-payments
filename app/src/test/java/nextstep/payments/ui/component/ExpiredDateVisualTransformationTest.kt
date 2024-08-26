package nextstep.payments.ui.component

import androidx.compose.ui.text.AnnotatedString
import junit.framework.TestCase.assertEquals
import org.junit.Test

class ExpiredDateVisualTransformationTest {
    @Test
    fun 만료일를_입력하면_만료일_형식에_맞게_변형되어_노출된다() {
        // given
        val expiredDate = "1234"
        val expectedFormattedExpiredDate = "12 / 34"

        // when
        val actualFormattedExpiredDate =
            ExpiredDateVisualTransformation().filter(AnnotatedString(expiredDate))

        // then
        assertEquals(expectedFormattedExpiredDate, actualFormattedExpiredDate.text.text)
    }

    @Test
    fun 만료일_월과_연도_첫번째_자리_입력되면_만료일_형식에_맞게_변형되어_노출된다() {
        // given
        val expiredDate = "121"
        val expectedFormattedExpiredDate = "12 / 1"

        // when
        val actualFormattedExpiredDate =
            ExpiredDateVisualTransformation().filter(AnnotatedString(expiredDate))

        // then
        assertEquals(expectedFormattedExpiredDate, actualFormattedExpiredDate.text)
    }

    @Test
    fun 만료일에_월만_입력하면_월만_노출된다() {
        // given
        val expiredDate = "12"
        val expectedFormattedExpiredDate = "12"

        // when
        val actualFormattedExpiredDate =
            ExpiredDateVisualTransformation().filter(AnnotatedString(expiredDate))

        // then
        assertEquals(expectedFormattedExpiredDate, actualFormattedExpiredDate.text.text)
    }
}
