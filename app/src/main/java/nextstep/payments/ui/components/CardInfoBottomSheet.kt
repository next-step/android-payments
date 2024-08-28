package nextstep.payments.ui.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import nextstep.payments.data.model.Bank

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardInfoBottomSheet(
    isBottomSheetVisible: Boolean,
    onBankSelect: (Bank) -> Unit,
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
        onDismissRequest = onDismissRequest
    ) {
        BankSelectRow(
            banks = Bank.entries,
            onBankSelect = { bank ->
                onBankSelect(bank)
            }
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
