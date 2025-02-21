package nextstep.payments.designsystem.transformed

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText

fun expiredDateTransformedText(text: AnnotatedString): TransformedText {

    val annotatedString = AnnotatedString.Builder().run {
        for (i in text.indices) {
            append(text[i])
            if (i == 1) {
                append(" / ")
            }
        }
        toAnnotatedString()
    }

    val phoneNumberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (offset <= 2) return offset
            if (offset <= 4) return offset + 3
            return offset + 3
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset <= 2) return offset
            if (offset <= 4) return offset - 3
            return offset - 3
        }
    }

    return TransformedText(annotatedString, phoneNumberOffsetTranslator)
}