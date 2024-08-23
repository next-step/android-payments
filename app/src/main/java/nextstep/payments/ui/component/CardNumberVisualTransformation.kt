package nextstep.payments.ui.component

import androidx.compose.runtime.Stable
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
        val trimmed = text.text.filter { it.isDigit() }.take(CARD_NUMBER_LENGTH)
        val formatted =
            buildString {
                trimmed.forEachIndexed { index, char ->
                    append(if (index >= DIGIT_SIZE * 2 && mask != null) mask else char)
                    if ((index + 1) % DIGIT_SIZE == 0 && index < trimmed.length - 1) {
                        append(
                            " ".repeat(delimiterSpacing) + delimiter +
                                " ".repeat(
                                    delimiterSpacing,
                                ),
                        )
                    }
                }
            }

        return TransformedText(
            AnnotatedString(formatted),
            offsetMapping =
                object : OffsetMapping {
                    override fun originalToTransformed(offset: Int): Int {
                        if (offset >= trimmed.length) return formatted.length

                        val delimiterCount = (offset / DIGIT_SIZE)
                        return offset + delimiterCount * (delimiterSpacing + 1)
                    }

                    override fun transformedToOriginal(offset: Int): Int {
                        if (offset >= formatted.length) return trimmed.length

                        val delimiterCount = (offset / (DIGIT_SIZE + delimiterSpacing + 1))
                        return offset - delimiterCount * (delimiterSpacing + 1)
                    }
                },
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CardNumberVisualTransformation) return false

        return delimiter == other.delimiter &&
            mask == other.mask &&
            delimiterSpacing == other.delimiterSpacing
    }

    override fun hashCode(): Int {
        var result = delimiter.hashCode()
        result = 31 * result + (mask?.hashCode() ?: 0)
        result = 31 * result + delimiterSpacing
        return result
    }

    companion object {
        private const val CARD_NUMBER_LENGTH = 16
        private const val DIGIT_SIZE = 4

        @Stable
        val DEFAULT = CardNumberVisualTransformation()

        @Stable
        val ASTERISK_MASKED =
            CardNumberVisualTransformation(
                mask = '*',
                delimiterSpacing = 2,
            )
    }
}
