package nextstep.payments.ui.newcard.component

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

sealed class NewCardVisualTransformation : VisualTransformation {
    val offsetTranslator: OffsetMapping = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (offset <= 3) return offset
            if (offset <= 7) return offset + 1
            if (offset <= 11) return offset + 2
            if (offset <= 16) return offset + 3
            return 19
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset <= 4) return offset
            if (offset <= 9) return offset - 1
            if (offset <= 14) return offset - 2
            if (offset <= 19) return offset - 3
            return 16
        }
    }

    data object CreditCardVisualTransformation : NewCardVisualTransformation() {
        override fun filter(text: AnnotatedString): TransformedText {
            val trimmed = if (text.text.length >= 16) text.text.substring(0..15) else text.text
            var output = ""
            for (i in trimmed.indices) {
                output += trimmed[i]
                if (i % 4 == 3 && i != 15) output += "-"
            }

            return TransformedText(AnnotatedString(output), offsetTranslator)
        }
    }

    data object ExpiredDateVisualTransformation : NewCardVisualTransformation() {
        override fun filter(text: AnnotatedString): TransformedText {
            val trimmed = if (text.text.length >= 4) text.text.substring(0..3) else text.text
            var output = ""
            for (i in trimmed.indices) {
                output += trimmed[i]
                if (i % 2 == 1 && i != 3) output += "/"
            }

            return TransformedText(AnnotatedString(output), offsetTranslator)
        }
    }
}
