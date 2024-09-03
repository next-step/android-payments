package nextstep.payments.ui.util

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

/**
 * 만료일 입력 시, 예를 들어 `0924`를 `09 / 24` 형식으로 변환하는 클래스입니다.
 */
internal class ExpirationDateVisualTransformation : VisualTransformation {

    override fun filter(text: AnnotatedString): TransformedText {
        val formattedText = buildString {
            text.forEachIndexed { index, ch ->
                append(ch)
                if (index == 1) append(" / ")
            }
        }

        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                return when {
                    offset <= 2 -> offset
                    offset <= 4 -> offset + 3 // 슬래시 및 공백의 길이만큼 오프셋 추가
                    else -> 7 // 모든 입력이 완료된 후의 최종 위치
                }
            }

            override fun transformedToOriginal(offset: Int): Int {
                return when {
                    offset <= 2 -> offset
                    offset <= 7 -> offset - 3 // 슬래시 및 공백의 길이만큼 오프셋 감소
                    else -> 4 // 최대 4자리 입력 후의 최종 위치
                }
            }
        }

        return TransformedText(AnnotatedString(formattedText), offsetMapping)
    }
}
