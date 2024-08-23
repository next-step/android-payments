package nextstep.payments.ui.component

import androidx.compose.runtime.Stable
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class ExpiredDateVisualTransformation(
    private val delimiter: Char = '/',
) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = text.text.filter { it.isDigit() }.take(4)
        val formatted =
            when (trimmed.length) {
                in 0..2 -> trimmed
                else -> "${trimmed.substring(0, 2)} $delimiter ${trimmed.substring(2)}"
            }

        return TransformedText(
            AnnotatedString(formatted),
            offsetMapping =
                object : OffsetMapping {
                    override fun originalToTransformed(offset: Int): Int =
                        when {
                            offset <= 2 -> offset
                            offset in 3..4 -> offset + DELIMITER_SPACING
                            else -> formatted.length
                        }

                    override fun transformedToOriginal(offset: Int): Int =
                        when {
                            offset <= 2 -> offset
                            offset in (3 + DELIMITER_SPACING)..(4 + DELIMITER_SPACING) -> offset - DELIMITER_SPACING
                            else -> trimmed.length
                        }
                },
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ExpiredDateVisualTransformation) return false

        return delimiter == other.delimiter
    }

    override fun hashCode(): Int = delimiter.hashCode()

    companion object {
        private const val DELIMITER_SPACING = 3

        @Stable
        val DEFAULT = ExpiredDateVisualTransformation()
    }
}
