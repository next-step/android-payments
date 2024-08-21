package nextstep.payments.ui.component

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class CardNumberVisualTransformation(
    private val delimiter: Char = '-',
    private val delimiterSpacing: Int = 1,
    private val mask: Char? = null,
) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = text.text.take(CARD_NUMBER_LENGTH)
        val spacing = " ".repeat(delimiterSpacing)

        val transformed =
            buildString {
                trimmed.forEachIndexed { index, char ->
                    when (index) {
                        in 0 until DIGIT_SIZE,
                        in DIGIT_SIZE until DIGIT_SIZE * 2,
                        -> append(char)

                        in DIGIT_SIZE * 2 until CARD_NUMBER_LENGTH -> append(mask ?: char)
                    }

                    if ((index + 1) % DIGIT_SIZE == 0 && index < CARD_NUMBER_LENGTH - 1) {
                        append("$spacing$delimiter$spacing")
                    }
                }
            }

        return TransformedText(
            AnnotatedString(transformed),
            offsetMapping =
                object : OffsetMapping {
                    override fun originalToTransformed(offset: Int): Int {
                        val delimiterCount = offset / DIGIT_SIZE
                        return offset + delimiterCount * (delimiterSpacing * 2 + 1)
                    }

                    override fun transformedToOriginal(offset: Int): Int {
                        val delimiterCount = offset / (DIGIT_SIZE + delimiterSpacing * 2 + 1)
                        return offset - delimiterCount * (delimiterSpacing * 2 + 1)
                    }
                },
        )
    }

    companion object {
        private const val CARD_NUMBER_LENGTH = 16
        private const val DIGIT_SIZE = 4
    }
}
