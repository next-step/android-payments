package nextstep.payments.ui.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class ExpiryDateVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val original = text.text.filter { it.isDigit() } // 숫자만 남김
        val transformed = buildString {
            for (i in original.indices) {
                if (i == 2) append(" / ") // 2번째 자리에서 '/' 추가
                append(original[i])
            }
        }

        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                return when {
                    offset <= 2 -> offset
                    offset <= 4 -> offset + 3 // ' / ' 추가된 길이 고려
                    else -> transformed.length // 최대값 제한
                }
            }

            override fun transformedToOriginal(offset: Int): Int {
                return when {
                    offset <= 2 -> offset
                    offset <= 5 -> offset - 3 // ' / ' 제거된 위치 계산
                    else -> original.length // 최대값 제한
                }
            }
        }

        return TransformedText(AnnotatedString(transformed), offsetMapping)
    }
}