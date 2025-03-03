package nextstep.payments.ui.utils

object TextFormatUtil {
    fun formatDate(date: String): String {
        val digits = date.filter { it.isDigit() } // 숫자만 남김
        return when {
            digits.length <= 2 -> digits // 2자리 이하 → 그대로 유지
            digits.length <= 4 -> "${digits.substring(0, 2)} / ${digits.substring(2)}" // 4자리일 경우 변환
            else -> "${digits.substring(0, 2)} / ${digits.substring(2, 4)}" // 4자리까지만 유지
        }
    }
}