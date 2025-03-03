package nextstep.payments.newcard.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import nextstep.payments.R
import nextstep.payments.model.BankType

enum class BankTypeUiModel(
    val bankType: BankType,
    val title: String,
    val color: Color,
    @DrawableRes val iconResId: Int?,
) {
    NOT_SELECTED(
        bankType = BankType.NOT_SELECTED,
        title = "",
        color = Color(0xFF333333),
        iconResId = null,
    ),
    KB(
        bankType = BankType.KB,
        title = "국민카드",
        color = Color(0xFF544E46),
        iconResId = R.drawable.bank_kb,
    ),
    BC(
        bankType = BankType.BC,
        title = "BC카드",
        color = Color(0xFFDE5457),
        iconResId = R.drawable.bank_bc,
    ),
    WOORI(
        bankType = BankType.WOORI,
        title = "우리카드",
        color = Color(0xFF3579C2),
        iconResId = R.drawable.bank_woori,
    ),
    SHINHAN(
        bankType = BankType.SHINHAN,
        title = "신한카드",
        color = Color(0xFF1C45F5),
        iconResId = R.drawable.bank_sinhan,
    ),
    KAKAOBANK(
        bankType = BankType.KAKAOBANK,
        title = "카카오뱅크",
        color = Color(0xFFFBE74D),
        iconResId = R.drawable.bank_kakaobank,
    ),
    HYUNDAI(
        bankType = BankType.HYUNDAI,
        title = "현대카드",
        color = Color(0xFF000000),
        iconResId = R.drawable.bank_hyundai,
    ),
    LOTTE(
        bankType = BankType.LOTTE,
        title = "롯데카드",
        color = Color(0xFFDA3832),
        iconResId = R.drawable.bank_lotte,
    ),
    HANA(
        bankType = BankType.HANA,
        title = "하나카드",
        color = Color(0xFF41928F),
        iconResId = R.drawable.bank_hana,
    );

    companion object {
        fun from(bankType: BankType): BankTypeUiModel {
            return entries.firstOrNull { it.bankType == bankType } ?: NOT_SELECTED
        }
    }
}