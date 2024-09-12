package nextstep.payments.component.bottomsheet.bank

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.payments.screen.model.BankTypeUiModel
import nextstep.payments.ui.theme.PaymentsTheme

private const val COLUMN_COUNT = 4

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BankSelectRow(
    bankTypeList: List<BankTypeUiModel>,
    onClick : (BankTypeUiModel) -> Unit,
    modifier: Modifier = Modifier
) {
    FlowRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(36.dp,),
        horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterHorizontally),
        verticalArrangement = Arrangement.spacedBy(23.dp),
        maxItemsInEachRow = COLUMN_COUNT
    ) {
        bankTypeList.forEach { bankType ->
            BankItem(
                bankType = bankType,
                modifier = Modifier.wrapContentHeight().weight(1f).clickable {
                    onClick(bankType)
                }.testTag(bankType.name)
            )
        }
    }
}

@Composable
fun BankItem(
    bankType: BankTypeUiModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(9.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = bankType.imageDrawableRes),
            contentDescription = bankType.name
        )
        Text(
            text = stringResource(id = bankType.nameStringRes),
            fontSize = 16.sp,
            fontWeight = FontWeight(500),
            color = Color(0xff525252)
        )
    }
}

@Preview(showBackground = true, name = "BankSelectRow")
@Composable
private fun Preview1() {
    PaymentsTheme {
        BankSelectRow(
            bankTypeList = BankTypeUiModel.entries,
            onClick = { }
        )
    }
}

@Preview(showBackground = true, name = "BankItem")
@Composable
private fun Preview2() {
    PaymentsTheme {
        BankItem(
            bankType = BankTypeUiModel.HYUNDAI
        )
    }
}
