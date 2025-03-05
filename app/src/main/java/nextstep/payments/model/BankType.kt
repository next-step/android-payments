package nextstep.payments.model

import androidx.compose.ui.graphics.Color

enum class BankType(val title: String, val color: Color = Color(0xFF333333)) {
    NOT_SELECTED(""),
    BC("BC카드", Color.Red),
    SHINHAN("신한카드", Color.Blue),
    KAKAO("카카오뱅크", Color.Yellow),
    HYUNDAI("현대카드", Color.DarkGray),
    WOORI("우리카드", Color.Cyan),
    LOTTE("롯데카드", Color.Magenta),
    HANA("하나카드", Color.Green),
    KB("국민카드", Color.LightGray)
}