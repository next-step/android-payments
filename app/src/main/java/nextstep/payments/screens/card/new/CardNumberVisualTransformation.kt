package nextstep.payments.screens.card.new

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class CardNumberVisualTransformation : VisualTransformation {
    private val offsetMapping = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            return when (offset) {
                in 0..4 -> offset
                in 5..8 -> offset + 1
                in 9..12 -> offset + 2
                in 13..15 -> offset + 3
                else -> offset + 3
            }
        }

        override fun transformedToOriginal(offset: Int): Int {
            return when (offset) {
                in 0..3 -> offset
                in 5..8 -> offset - 1
                in 10..13 -> offset - 2
                in 15..18 -> offset - 3
                else -> offset - 3
            }
        }
    }

    override fun filter(text: AnnotatedString): TransformedText {
        val formattedString = buildString {
            text.text.forEachIndexed { index, c ->
                if (index >= GROUP_SIZE && index % GROUP_SIZE == 0) {
                    append(SEPARATOR)
                }
                append(c)
            }
        }

        return TransformedText(AnnotatedString(formattedString), offsetMapping)
    }

    companion object {
        private const val GROUP_SIZE = 4
        private const val SEPARATOR: String = "-"
    }
}
