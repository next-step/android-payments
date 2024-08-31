package nextstep.payments.component.textfield

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

/**
 * https://developer.android.com/reference/kotlin/androidx/compose/ui/text/input/VisualTransformation 참고
 */
class CardNumberVisualTransformation(
    private val separator: String = " - "
) : VisualTransformation {

    companion object {
        private const val MAX_LENGTH = 16
        private const val CHUNK_SIZE = 4
    }

    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = if (text.text.length >= MAX_LENGTH) {
            text.text.substring(0 until MAX_LENGTH)
        } else {
            text.text
        }

        val out = buildString {
            for (i in trimmed.indices) {
                append(trimmed[i])
                if (i % CHUNK_SIZE == (CHUNK_SIZE - 1) && i != MAX_LENGTH - 1) {
                    append(separator)
                }
            }
        }

        val transformedLength = trimmed.length + (trimmed.length / CHUNK_SIZE) * separator.length

        val cardNumberOffsetTranslator = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                return when {
                    offset <= 3 -> offset
                    offset <= 7 -> offset + 3
                    offset <= 11 -> offset + 6
                    offset <= MAX_LENGTH -> offset + 9
                    else -> transformedLength
                }
            }

            override fun transformedToOriginal(offset: Int): Int {
                return when {
                    offset <= 4 -> offset
                    offset <= 11 -> offset - 3
                    offset <= 18 -> offset - 6
                    offset <= 25 -> offset - 9
                    else -> MAX_LENGTH
                }
            }
        }

        return TransformedText(
            AnnotatedString(out),
            cardNumberOffsetTranslator
        )
    }
}

class ExpiredDateVisualTransformation(
    private val separator: String = " / "
) : VisualTransformation {

    companion object {
        private const val MAX_LENGTH = 4
        private const val CHUNK_SIZE = 2
    }

    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = if (text.text.length >= MAX_LENGTH) {
            text.text.substring(0 until MAX_LENGTH)
        } else {
            text.text
        }

        val out = buildString {
            for (i in trimmed.indices) {
                append(trimmed[i])
                if (i % CHUNK_SIZE == (CHUNK_SIZE - 1) && i != MAX_LENGTH - 1) {
                    append(separator)
                }
            }
        }

        val transformedLength = trimmed.length + (trimmed.length / CHUNK_SIZE) * separator.length

        val expiredDateOffsetTranslator = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                return when {
                    offset <= 1 -> offset
                    offset <= 4 -> offset + 3
                    else -> transformedLength
                }
            }

            override fun transformedToOriginal(offset: Int): Int {
                return when {
                    offset <= 2 -> offset
                    offset <= 7 -> offset - 3
                    else -> MAX_LENGTH
                }
            }
        }

        return TransformedText(
            AnnotatedString(out),
            expiredDateOffsetTranslator
        )
    }
}