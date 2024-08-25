package nextstep.payments.ui.screen.newcard.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import nextstep.payments.ui.icons.Bc
import nextstep.payments.ui.icons.Hana
import nextstep.payments.ui.icons.Hyundai
import nextstep.payments.ui.icons.Kakao
import nextstep.payments.ui.icons.Kb
import nextstep.payments.ui.icons.Lotte
import nextstep.payments.ui.icons.MyIconPack
import nextstep.payments.ui.icons.Shinhan
import nextstep.payments.ui.icons.Woori
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
    val image: ImageVector
) {
    BC("BC", BcColor, MyIconPack.Bc),
    HANA("HANA", HanaColor, MyIconPack.Hana),
    HYUNDAI("Hyundai", HyundaiColor, MyIconPack.Hyundai),
    KAKAO("Kakao", KakaoColor, MyIconPack.Kakao),
    KB("KB", KbColor, MyIconPack.Kb),
    LOTTE("Lotte", LotteColor, MyIconPack.Lotte),
    SHINHAN("Shinhan", ShinhanColor, MyIconPack.Shinhan),
    WOORI("Woori", WooriColor, MyIconPack.Woori);
}
