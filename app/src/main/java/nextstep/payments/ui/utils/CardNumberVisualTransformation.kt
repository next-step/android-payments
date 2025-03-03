package nextstep.payments.ui.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class CardNumberVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val original = text.text.filter { it.isDigit() } // 숫자만 남김
        val transformed = buildString {
            for (i in original.indices) {
                if (i > 0 && i % 4 == 0) append(" - ") // 4자리마다 '-' 추가
                append(original[i])
            }
        }

        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                val extraOffset = (offset / 4) * 3 // '-' 포함된 인덱스 계산
                return (offset + extraOffset).coerceAtMost(transformed.length)
            }

            override fun transformedToOriginal(offset: Int): Int {
                val pureIndex = offset - (offset / 7) * 3 // '-' 제거된 원본 인덱스 계산
                return pureIndex.coerceAtMost(original.length)
            }
        }

        return TransformedText(AnnotatedString(transformed), offsetMapping)
    }
}