package nextstep.payments.ui.newcard.component

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

sealed interface NewCardVisualTransformation : VisualTransformation {
    data object CreditCardVisualTransformation : NewCardVisualTransformation {
        private val offsetTranslator: OffsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int = when {
                offset <= 3 -> offset
                offset <= 7 -> offset + 1
                offset <= 11 -> offset + 2
                offset <= 16 -> offset + 3
                else -> 19
            }

            override fun transformedToOriginal(offset: Int): Int = when {
                offset <= 4 -> offset
                offset <= 9 -> offset - 1
                offset <= 14 -> offset - 2
                offset <= 19 -> offset - 3
                else -> 16
            }
        }

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

    data object ExpiredDateVisualTransformation : NewCardVisualTransformation {
        private val offsetTranslator = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int = when {
                offset <= 1 -> offset
                offset == 2 -> offset + 1
                offset <= 4 -> offset + 1
                else -> 5
            }

            override fun transformedToOriginal(offset: Int): Int = when {
                offset <= 2 -> offset
                offset == 3 -> offset - 1
                offset <= 5 -> offset - 1
                else -> 4
            }
        }

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
