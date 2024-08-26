package nextstep.payments.ui.screen.newcard.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import nextstep.payments.R
import nextstep.payments.data.BankType
import nextstep.payments.ui.theme.BcColor
import nextstep.payments.ui.theme.HanaColor
import nextstep.payments.ui.theme.HyundaiColor
import nextstep.payments.ui.theme.KakaoColor
import nextstep.payments.ui.theme.KbColor
import nextstep.payments.ui.theme.LotteColor
import nextstep.payments.ui.theme.ShinhanColor
import nextstep.payments.ui.theme.WooriColor

enum class BankTypeModel(
    val companyName: String,
    val color: Color,
    @DrawableRes val imageRes: Int?
) {
    NOT_SELECTED("", Color.Gray, null),
    BC("BC카드", BcColor, R.drawable.bc),
    HANA("하나카드", HanaColor, R.drawable.hana),
    HYUNDAI("현대카드", HyundaiColor, R.drawable.hyundai),
    KAKAO("카카오뱅크", KakaoColor, R.drawable.kakao),
    KB("국민카드", KbColor, R.drawable.kb),
    LOTTE("롯데카드", LotteColor, R.drawable.lotte),
    SHINHAN("신한카드", ShinhanColor, R.drawable.shinhan),
    WOORI("우리카드", WooriColor, R.drawable.woori);

    companion object {
        fun getCardBrandList(): List<BankTypeModel> {
            return BankTypeModel.entries.filter { it != NOT_SELECTED }
        }
    }
}

internal fun BankTypeModel.toData(): BankType {
    return when (this) {
        BankTypeModel.NOT_SELECTED -> BankType.NOT_SELECTED
        BankTypeModel.BC -> BankType.BC
        BankTypeModel.HANA -> BankType.HANA
        BankTypeModel.HYUNDAI -> BankType.HYUNDAI
        BankTypeModel.KAKAO -> BankType.KAKAO
        BankTypeModel.KB -> BankType.KB
        BankTypeModel.LOTTE -> BankType.LOTTE
        BankTypeModel.SHINHAN -> BankType.SHINHAN
        BankTypeModel.WOORI -> BankType.WOORI
    }
}

internal fun BankType.toUiModel(): BankTypeModel {
    return when (this) {
        BankType.NOT_SELECTED -> BankTypeModel.NOT_SELECTED
        BankType.BC -> BankTypeModel.BC
        BankType.HANA -> BankTypeModel.HANA
        BankType.HYUNDAI -> BankTypeModel.HYUNDAI
        BankType.KAKAO -> BankTypeModel.KAKAO
        BankType.KB -> BankTypeModel.KB
        BankType.LOTTE -> BankTypeModel.LOTTE
        BankType.SHINHAN -> BankTypeModel.SHINHAN
        BankType.WOORI -> BankTypeModel.WOORI
    }
}
