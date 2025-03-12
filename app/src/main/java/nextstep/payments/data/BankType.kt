package nextstep.payments.data

import androidx.compose.ui.graphics.Color
import nextstep.payments.R

enum class BankType(
    val krName: String,
    val icon: Int,
    val description: String,
    val cardColor: Color,
) {
    BC("BC카드", R.drawable.ic_bc, "BC", Color(0xFFF04651)),
    SHINHAN("신한카드", R.drawable.ic_shinhan, "SHINHAN", Color(0xFF0046FF)),
    KAKAO("카카오뱅크", R.drawable.ic_kakao_bank, "KAKAO", Color(0xFFFFE600)),
    HYUNDAI("현대카드", R.drawable.ic_hyundai, "HYUNDAI", Color(0xFF000000)),
    WOORI("우리카드", R.drawable.ic_woori, "WOORI", Color(0xFF007BC8)),
    LOOTE("롯데카드", R.drawable.ic_lotte, "LOTTE", Color(0xFFED1C24)),
    HANA("하나카드", R.drawable.ic_hana, "HANA", Color(0xFF009490)),
    KB("국민카드", R.drawable.ic_kb, "KB", Color(0xFF685E54))
}