package nextstep.payments.util

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

/**
 * `TextField` 함수에서 숫자 형식에 하이픈('-')을 자동으로 삽입해주는 클래스입니다
 */
internal class CardNumberVisualTransformation : VisualTransformation {

    override fun filter(text: AnnotatedString): TransformedText {
        require(text.all { it.isDigit() }) { "CardNumberVisualTransformation 클래스는 숫자만 입력 가능합니다" }

        val formattedText = buildString {
            text.forEachIndexed { index, char ->
                append(char)
                if ((index + 1) % 4 == 0 && text.length > index + 1) {
                    append("-")
                }
            }
        }

        val offsetMapping = CardNumberOffsetMapping(formattedText)

        return TransformedText(AnnotatedString(formattedText), offsetMapping)
    }

    private class CardNumberOffsetMapping(private val formattedText: String) : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            return formattedText.length
        }

        override fun transformedToOriginal(offset: Int): Int {
            return offset - offset / 5
        }
    }
}
