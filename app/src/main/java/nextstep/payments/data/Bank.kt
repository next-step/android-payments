package nextstep.payments.data

import androidx.compose.ui.graphics.Color

enum class BankType {
    NOT_SELECTED, BC, SHINHAN, KAKAO, HYUNDAI, WOORI, LOTTE, HANA, KB
}

data class Bank(
    val bankType: BankType = BankType.NOT_SELECTED,
    val name: String = "",
    val iconUrl: String = "",
    val color: Color = Color.Black
)