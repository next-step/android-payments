@file:OptIn(ExperimentalMaterial3Api::class)

package nextstep.payments.ui.add

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import nextstep.payments.data.model.BankType
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
internal fun BankSelectBottomSheet(
    onBankSelect: (BankType) -> Unit,
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(confirmValueChange = { false })
) {
    val scope = rememberCoroutineScope()

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = {},
        modifier = modifier.semantics {
            contentDescription = "카드사 선택 목록 보기"
        },
        properties = ModalBottomSheetProperties(shouldDismissOnBackPress = false)
    ) {
        BanksContainer(
            bankTypes = BankType.getSelectableTypes(),
            onBankSelect = { bank ->
                scope.launch {
                    sheetState.hide()
                }.invokeOnCompletion {
                    onBankSelect(bank)
                }
            }
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun BanksContainer(
    bankTypes: List<BankType>,
    onBankSelect: (BankType) -> Unit,
    modifier: Modifier = Modifier,
    columnCount: Int = 4,
) {
    FlowRow(
        maxItemsInEachRow = columnCount,
        verticalArrangement = Arrangement.spacedBy(23.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 44.dp, vertical = 36.dp)
            .semantics {
                contentDescription = "카드사 목록"
            },
    ) {
        bankTypes.forEach {
            Box(
                modifier = Modifier.weight(1f)
            ) {
                BankItem(
                    bankType = it,
                    onBankSelect = onBankSelect,
                    modifier =
                    Modifier
                        .align(Alignment.Center),
                )
            }
        }
    }
}

@Composable
private fun BankItem(
    bankType: BankType,
    onBankSelect: (BankType) -> Unit,
    modifier: Modifier = Modifier
) {
    val bankName = stringResource(bankType.nameResId)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .clickable(
                onClick = { onBankSelect(bankType) }
            ).semantics {
                contentDescription = bankName
            },
    ) {
        Image(
            painter = painterResource(bankType.iconResId),
            contentDescription = null,
            modifier = Modifier.size(37.dp)
        )
        Spacer(modifier = Modifier.height(9.dp))

        Text(
            text = bankName,
            style = MaterialTheme.typography.titleMedium,
            color = Color(0xFF525252),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BankSelectBottomSheetPreview() {
    PaymentsTheme {
        BankSelectBottomSheet(
            sheetState = rememberStandardBottomSheetState(),
            onBankSelect = {},
        )
    }
}
