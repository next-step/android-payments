package nextstep.payments.model

import androidx.compose.ui.graphics.Color

enum class BankType {
    NOT_SELECTED,
    BC,
    SHINHAN,
    KAKAO,
    HYUNDAI,
    WOORI,
    LOTTE,
    HANA,
    KB
}

fun BankType.toName(): String {
    return when (this) {
        BankType.NOT_SELECTED -> ""
        BankType.BC -> "BC카드"
        BankType.SHINHAN -> "신한카드"
        BankType.KAKAO -> "카카오뱅크"
        BankType.HYUNDAI -> "현대카드"
        BankType.WOORI -> "우리카드"
        BankType.LOTTE -> "롯데카드"
        BankType.HANA -> "하나카드"
        BankType.KB -> "국민카드"
    }
}

fun BankType.toColor(): Color {
    return when (this) {
        BankType.NOT_SELECTED -> Color(0xFF333333)
        BankType.BC -> Color.Red
        BankType.SHINHAN -> Color.Blue
        BankType.KAKAO -> Color.Yellow
        BankType.HYUNDAI -> Color.DarkGray
        BankType.WOORI -> Color.Cyan
        BankType.LOTTE -> Color.Magenta
        BankType.HANA -> Color.Green
        BankType.KB -> Color.LightGray
    }
}