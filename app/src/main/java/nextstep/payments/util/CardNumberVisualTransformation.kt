package nextstep.payments.util

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class CardNumberVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val formattedText = buildString {
            text.text.chunked(4).forEachIndexed { index, chunk ->
                append(chunk)
                if (index < text.text.length / 4 - 1) append(" - ")
            }
        }

        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                return when (offset) {
                    in 0..3 -> offset
                    in 4..7 -> offset + 1
                    in 8..11 -> offset + 2
                    else -> offset + 3
                }
            }

            override fun transformedToOriginal(offset: Int): Int {
                return when (offset) {
                    in 0..4 -> offset
                    in 5..9 -> offset - 1
                    in 10..14 -> offset - 2
                    else -> offset - 3
                }
            }
        }

        return TransformedText(AnnotatedString(formattedText), offsetMapping)
    }
}