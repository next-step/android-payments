package nextstep.payments.feature.newcard.view

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import nextstep.payments.model.BankType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BankSelectBottomSheet(
    modalBottomSheetState: SheetState,
    setBankType: (BankType) -> Unit = {}
) {
    ModalBottomSheet(
        sheetState = modalBottomSheetState,
        onDismissRequest = { },
    ) {
        BankSelectRow(
            onClick = setBankType
        )
    }
}