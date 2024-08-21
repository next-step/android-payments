package nextstep.payments.ui.component

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class CardNumberVisualTransformation(
    private val delimiter: Char = '-',
) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        // 1234567890123456 -> 1234 - 5678 - 9012 - 3456
        val trimmed = text.text.replace(" $delimiter ", "").take(16)
        val formatted =
            buildString {
                trimmed.forEachIndexed { index, c ->
                    append(c)
                    if ((index + 1) % DIGIT_GROUP_SIZE == 0 && index < trimmed.length - 1) {
                        append(" $delimiter ")
                    }
                }
            }

        return TransformedText(
            AnnotatedString(formatted),
            offsetMapping =
                object : OffsetMapping {
                    override fun originalToTransformed(offset: Int): Int {
                        val delimiterCount = offset / DIGIT_GROUP_SIZE
                        return offset + (delimiterCount * DELIMITER_SPACING)
                    }

                    override fun transformedToOriginal(offset: Int): Int {
                        val delimiterCount = offset / (DIGIT_GROUP_SIZE + DELIMITER_SPACING)
                        return offset - (delimiterCount * DELIMITER_SPACING)
                    }
                },
        )
    }

    companion object {
        private const val DIGIT_GROUP_SIZE = 4
        private const val DELIMITER_SPACING = 3
    }
}
