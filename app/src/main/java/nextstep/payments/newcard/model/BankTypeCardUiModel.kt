package nextstep.payments.newcard.model

import androidx.compose.ui.graphics.Color
import nextstep.payments.model.BankType

enum class BankTypeCardUiModel(
    val bankType: BankType,
    val title: String,
    val color: Color,
) {
    NOT_SELECTED(
        bankType = BankType.NOT_SELECTED,
        title = "",
        color = Color(0xFF333333),
    ),
    KB(
        bankType = BankType.KB,
        title = "국민카드",
        color = Color(0xFF544E46),
    ),
    BC(
        bankType = BankType.BC,
        title = "BC카드",
        color = Color(0xFFDE5457),
    ),
    WOORI(
        bankType = BankType.WOORI,
        title = "우리카드",
        color = Color(0xFF3579C2),
    ),
    SHINHAN(
        bankType = BankType.SHINHAN,
        title = "신한카드",
        color = Color(0xFF1C45F5),
    ),
    KAKAOBANK(
        bankType = BankType.KAKAOBANK,
        title = "카카오뱅크",
        color = Color(0xFFFBE74D),
    ),
    HYUNDAI(
        bankType = BankType.HYUNDAI,
        title = "현대카드",
        color = Color(0xFF000000),
    ),
    LOTTE(
        bankType = BankType.LOTTE,
        title = "롯데카드",
        color = Color(0xFFDA3832),
    ),
    HANA(
        bankType = BankType.HANA,
        title = "하나카드",
        color = Color(0xFF41928F),
    );

    companion object {
        fun from(bankType: BankType): BankTypeCardUiModel {
            return entries.first { it.bankType == bankType }
        }
    }
}