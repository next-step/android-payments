package nextstep.payments.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import nextstep.payments.R

enum class BankType {
    BC,
    SHINHAN,
    KAKAO,
    HYUNDAI,
    WOORI,
    LOTTE,
    HANA,
    KB
}

@Composable
fun BankType.toName(): String {
    return when (this) {
        BankType.BC -> stringResource(R.string.bank_BC)
        BankType.SHINHAN -> stringResource(R.string.bank_SHINHAN)
        BankType.KAKAO -> stringResource(R.string.bank_KAKAO)
        BankType.HYUNDAI -> stringResource(R.string.bank_HYUNDAI)
        BankType.WOORI -> stringResource(R.string.bank_WOORI)
        BankType.LOTTE -> stringResource(R.string.bank_LOTTE)
        BankType.HANA -> stringResource(R.string.bank_HANA)
        BankType.KB -> stringResource(R.string.bank_KB)
    }
}

fun BankType.toColor(): Color {
    return when (this) {
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