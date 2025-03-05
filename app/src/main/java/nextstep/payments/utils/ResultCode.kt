package nextstep.payments.utils

import androidx.annotation.StringRes
import nextstep.payments.R

enum class ResultCode(
    @StringRes val stringResId: Int,
) {
    SUCCESS_ADD_CARD(stringResId = R.string.success_add_card),
    SUCCESS_MODIFY_CARD(stringResId = R.string.success_modify_card),
    SELECT_CARD_COMPANY(stringResId = R.string.request_select_card_company),
    NOTHING_TO_MODIFY(stringResId = R.string.nothing_to_modify_card),
    IDLE(stringResId = 0),
}