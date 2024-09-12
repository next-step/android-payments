package nextstep.payments.component.bottomsheet.bank

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import nextstep.payments.screen.model.BankTypeUiModel
import nextstep.payments.ui.theme.PaymentsTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BankSelectBottomSheet(
    onBankTypeClick : (BankTypeUiModel) -> Unit,
    onDismissRequest : () -> Unit,
    modalBottomSheetState : SheetState,
    modifier: Modifier = Modifier,
    containerColor : Color = Color.White
) {
    ModalBottomSheet(
        modifier = modifier,
        sheetState = modalBottomSheetState,
        containerColor = containerColor,
        onDismissRequest = onDismissRequest,
    ) {
        BankSelectRow(
            modifier = Modifier.navigationBarsPadding(),
            bankTypeList = BankTypeUiModel.entries,
            onClick = onBankTypeClick
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, name = "BankSelectBottomSheet")
@Composable
private fun Preview1() {
    var selectedBank by remember {
        mutableStateOf<BankTypeUiModel?>(null)
    }
    val modalBottomSheetState = rememberModalBottomSheetState()

    LaunchedEffect(key1 = selectedBank) {
        if (selectedBank != null) {
            modalBottomSheetState.hide()
        }
    }

    PaymentsTheme {
        BankSelectBottomSheet(
            onBankTypeClick = { bankType ->
                selectedBank = bankType
            },
            modalBottomSheetState = modalBottomSheetState,
            onDismissRequest = {}
        )
    }
}
