package nextstep.payments.ui.screen.newcard.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import nextstep.payments.R
import nextstep.payments.data.BankTypeData
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
    BC("BC카드", BcColor, R.drawable.bc),
    HANA("하나카드", HanaColor, R.drawable.hana),
    HYUNDAI("현대카드", HyundaiColor, R.drawable.hyundai),
    KAKAO("카카오뱅크", KakaoColor, R.drawable.kakao),
    KB("국민카드", KbColor, R.drawable.kb),
    LOTTE("롯데카드", LotteColor, R.drawable.lotte),
    SHINHAN("신한카드", ShinhanColor, R.drawable.shinhan),
    WOORI("우리카드", WooriColor, R.drawable.woori);
}

internal fun BankTypeModel.toData(): BankTypeData {
    return when (this) {
        BankTypeModel.BC -> BankTypeData.BC
        BankTypeModel.HANA -> BankTypeData.HANA
        BankTypeModel.HYUNDAI -> BankTypeData.HYUNDAI
        BankTypeModel.KAKAO -> BankTypeData.KAKAO
        BankTypeModel.KB -> BankTypeData.KB
        BankTypeModel.LOTTE -> BankTypeData.LOTTE
        BankTypeModel.SHINHAN -> BankTypeData.SHINHAN
        BankTypeModel.WOORI -> BankTypeData.WOORI
    }
}

internal fun BankTypeData.toUiModel(): BankTypeModel {
    return when (this) {
        BankTypeData.BC -> BankTypeModel.BC
        BankTypeData.HANA -> BankTypeModel.HANA
        BankTypeData.HYUNDAI -> BankTypeModel.HYUNDAI
        BankTypeData.KAKAO -> BankTypeModel.KAKAO
        BankTypeData.KB -> BankTypeModel.KB
        BankTypeData.LOTTE -> BankTypeModel.LOTTE
        BankTypeData.SHINHAN -> BankTypeModel.SHINHAN
        BankTypeData.WOORI -> BankTypeModel.WOORI
    }
}
