package nextstep.payments.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.payments.data.BankType


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BankSelectBottomSheet(
    stateSheet: SheetState,
    selectBank: (BankType) -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
) {

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = stateSheet,
        modifier = modifier.testTag("Card_BankSelectBottomSheet"),
    ) {
        BankSelectRow(
            onClick = {
                selectBank(it)
            },
        )
    }
}

private const val COLUMN_COUNT = 4

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun BankSelectRow(
    onClick: (bankType: BankType) -> Unit,
    modifier: Modifier = Modifier,
) {
    FlowRow(
        modifier = modifier
            .fillMaxWidth()
            .height(280.dp)
            .padding(horizontal = 32.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalArrangement = Arrangement.SpaceEvenly,
        maxItemsInEachRow = COLUMN_COUNT,
    ) {
        BankType.entries.forEach { bankType ->
            BankItem(
                bank = bankType,
                onClick = {
                    onClick(bankType)
                },
                modifier = Modifier.size(width = 70.dp, height = 68.dp)
            )
        }
    }
}

@Composable
private fun BankItem(
    bank: BankType,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .clickable {
                onClick()
            },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(bank.icon),
            contentDescription = bank.description,
        )
        Spacer(modifier = Modifier.height(9.dp))
        Text(
            text = bank.krName,
            fontSize = 16.sp,
            fontWeight = FontWeight.W500,
            letterSpacing = (16.sp * -0.08),
            lineHeight = 18.75.sp,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun BankSelectBottomSheetPreview() {
    val sheetState = SheetState(
        initialValue = SheetValue.Expanded,
        skipPartiallyExpanded = true,
        density = Density(1f),
    )

    BankSelectBottomSheet(
        stateSheet = sheetState,
        selectBank = {},
        onDismissRequest = {},
    )
}
