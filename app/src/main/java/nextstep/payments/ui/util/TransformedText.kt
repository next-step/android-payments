package nextstep.payments.ui.util

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText

fun String.toCardNumberTransformedText(): TransformedText {
    val annotatedString = AnnotatedString
        .Builder()
        .apply {
            take(16).forEachIndexed { i, c ->
                append(if (i < 8) c else '*')
                if (i == 3 || i == 7 || i == 11) append('-')
            }
        }
        .toAnnotatedString()

    val offsetMapping = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (offset <= 3) return offset
            if (offset <= 7) return offset + 1
            if (offset <= 11) return offset + 2
            if (offset <= 15) return offset + 3
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

    return TransformedText(annotatedString, offsetMapping)
}

fun String.toCardExpiredDateTransformedText(): TransformedText {
    val annotatedString = AnnotatedString
        .Builder()
        .apply {
            take(4).forEachIndexed { i, c ->
                append(c)
                if (i == 1) append('/')
            }
        }
        .toAnnotatedString()

    val offsetMapping = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (offset <= 1) return offset
            if (offset <= 3) return offset + 1
            return 5
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset <= 1) return offset
            if (offset <= 5) return offset - 1
            return 4
        }
    }

    return TransformedText(annotatedString, offsetMapping)
}
