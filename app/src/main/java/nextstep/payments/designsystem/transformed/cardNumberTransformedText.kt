package nextstep.payments.designsystem.transformed

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText


fun cardNumberTransformedText(text: AnnotatedString): TransformedText {

    val annotatedString = AnnotatedString.Builder().run {
        for (i in text.indices) {
            append(text[i])
            if (i == 3 || i == 7 || i == 11) {
                append(" – ")
            }
        }
        toAnnotatedString()
    }

    val phoneNumberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (offset <= 3) return offset
            if (offset <= 7) return offset + 3
            if (offset <= 11) return offset + 6
            if (offset <= 15) return offset + 9
            return offset + 9
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset <= 3) return offset
            if (offset <= 7) return offset - 3
            if (offset <= 11) return offset - 6
            if (offset <= 15) return offset - 9
            return offset - 9
        }
    }

    return TransformedText(annotatedString, phoneNumberOffsetTranslator)
}