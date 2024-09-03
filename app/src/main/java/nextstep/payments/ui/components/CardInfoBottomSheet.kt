package nextstep.payments.ui.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import nextstep.payments.ui.newcard.model.BankUI

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardInfoBottomSheet(
    isBottomSheetVisible: Boolean,
    onBankSelect: (BankUI) -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    val modalBottomSheetState = rememberModalBottomSheetState(
        confirmValueChange = { false }
    )

    LaunchedEffect(isBottomSheetVisible) {
        if (isBottomSheetVisible)
            modalBottomSheetState.show()
        else
            modalBottomSheetState.hide()
    }

    ModalBottomSheet(
        sheetState = modalBottomSheetState,
        onDismissRequest = onDismissRequest,
        modifier = modifier.testTag("cardInfoBottomSheet")
    ) {
        BankSelectRow(
            banks = BankUI.entries,
            onBankSelect = onBankSelect
        )
    }
}

@Preview
@Composable
private fun CardInfoBottomSheetPreview() {
    CardInfoBottomSheet(
        isBottomSheetVisible = false,
        onBankSelect = { },
        onDismissRequest= {}
    )
}
