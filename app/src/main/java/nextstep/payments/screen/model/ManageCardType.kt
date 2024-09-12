package nextstep.payments.screen.model

import nextstep.payments.screen.model.arg.CardArgType

enum class ManageCardType {
    ADD,
    EDIT
}

fun CardArgType.toManageCardType() : ManageCardType
    = when(this){
        is CardArgType.AddCardArg -> ManageCardType.ADD
        is CardArgType.EditCardArg -> ManageCardType.EDIT
    }

