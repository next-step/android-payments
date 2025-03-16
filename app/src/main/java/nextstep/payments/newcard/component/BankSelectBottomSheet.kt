package nextstep.payments.newcard.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.payments.common.model.Bank
import nextstep.payments.ui.theme.PaymentsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BankSelectBottomSheet(
    onDismissRequest: () -> Unit,
    sheetState: SheetState,
    onClickBank: (Bank) -> Unit,
    modifier: Modifier = Modifier
) {
    ModalBottomSheet(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        sheetState = sheetState
    ) {
        BankSelectBottomSheetContent(
            banks = Bank.entries,
            onClickBank = onClickBank,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}

private const val MAX_ITEMS_ROW = 4

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun BankSelectBottomSheetContent(
    banks: List<Bank>,
    onClickBank: (Bank) -> Unit,
    modifier: Modifier = Modifier
) {
    FlowRow(
        modifier = modifier.padding(horizontal = 47.dp, vertical = 36.dp),
        verticalArrangement = Arrangement.spacedBy(23.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        maxItemsInEachRow = MAX_ITEMS_ROW,
    ) {
        banks.forEach {
            BankItem(
                bank = it,
                onClick = { onClickBank(it) },
                modifier = Modifier.width(68.dp)
            )
        }
    }
}

@Composable
fun BankItem(
    bank: Bank,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(bank.imageRes),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(9.dp))
        Text(
            text = stringResource(bank.titleRes),
            fontWeight = FontWeight.W500,
            fontSize = 16.sp,
            lineHeight = 16.sp,
            letterSpacing = (-1.36).sp,
            color = Color(0xFF525252)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun BankSelectBottomSheetPreview() {
    PaymentsTheme {
        BankSelectBottomSheet(
            sheetState = rememberModalBottomSheetState(),
            onClickBank = {},
            onDismissRequest = {},
        )
    }
}
