package nextstep.payments.ui.screen.newcard.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import nextstep.payments.R
import nextstep.payments.ui.theme.BcColor
import nextstep.payments.ui.theme.HanaColor
import nextstep.payments.ui.theme.HyundaiColor
import nextstep.payments.ui.theme.KakaoColor
import nextstep.payments.ui.theme.KbColor
import nextstep.payments.ui.theme.LotteColor
import nextstep.payments.ui.theme.ShinhanColor
import nextstep.payments.ui.theme.WooriColor

enum class CardCompany(
    val companyName: String,
    val color: Color,
    @DrawableRes val imageRes: Int
) {
    BC("BC", BcColor, R.drawable.bc),
    HANA("HANA", HanaColor, R.drawable.hana),
    HYUNDAI("Hyundai", HyundaiColor, R.drawable.hyundai),
    KAKAO("Kakao", KakaoColor, R.drawable.kakao),
    KB("KB", KbColor, R.drawable.kb),
    LOTTE("Lotte", LotteColor, R.drawable.lotte),
    SHINHAN("Shinhan", ShinhanColor, R.drawable.shinhan),
    WOORI("Woori", WooriColor, R.drawable.woori);
}
