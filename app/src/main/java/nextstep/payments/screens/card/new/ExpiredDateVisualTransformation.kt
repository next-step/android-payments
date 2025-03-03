package nextstep.payments.screens.card.new

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class ExpiredDateVisualTransformation: VisualTransformation {
    private val offsetMapping = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            return when (offset) {
                in 0..2 -> offset
                in 3..4 -> offset + 1
                else -> offset + 1
            }
        }

        override fun transformedToOriginal(offset: Int): Int {
            return when (offset) {
                in 0..2 -> offset
                in 3..4 -> offset - 1
                else -> offset - 1
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
        private const val GROUP_SIZE = 2
        private const val SEPARATOR: String = "/"
    }
}
