package nextstep.payments.ui.transformation

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

object ExpiredDateTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val trimmedText = if (text.text.length > 4) text.text.substring(0..3) else text.text
        var transformedText = ""
        trimmedText.forEachIndexed { index, char ->
            transformedText += char
            if ((index + 1) % 2 == 0 && index != 3) transformedText += "/"
        }

        val cardOffsetTranslator = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                return when {
                    offset < 2 -> offset
                    offset < 4 -> offset + 1
                    else -> 5
                }
            }

            override fun transformedToOriginal(offset: Int): Int {
                return when {
                    offset <= 2 -> offset
                    offset <= 5 -> offset - 1
                    else -> 4
                }
            }
        }

        return TransformedText(AnnotatedString(transformedText), cardOffsetTranslator)
    }
}