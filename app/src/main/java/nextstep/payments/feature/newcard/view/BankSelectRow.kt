package nextstep.payments.feature.newcard.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.payments.model.BankType
import nextstep.payments.model.toColor
import nextstep.payments.model.toName

private const val COLUMN_COUNT = 4

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BankSelectRow(
    onClick: (BankType) -> Unit
) {
    FlowRow(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalArrangement = Arrangement.spacedBy(24.dp),
        maxItemsInEachRow = COLUMN_COUNT
    ) {
        BankType.entries.forEach { bank ->
            BankItem(
                bankType = bank,
                onClick = onClick,
                modifier = Modifier.width(80.dp)
            )
        }
    }
}

@Composable
private fun BankItem(
    bankType: BankType,
    onClick: (BankType) -> Unit,
    modifier: Modifier = Modifier,
) {
    if (bankType == BankType.NOT_SELECTED) return

    Column(
        modifier = modifier.clickable { onClick(bankType) },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .background(bankType.toColor(), CircleShape)
        )
        Text(
            text = bankType.toName(),
            fontSize = 16.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BankSelectRowPreview() {
    BankSelectRow(
        onClick = {}
    )
}