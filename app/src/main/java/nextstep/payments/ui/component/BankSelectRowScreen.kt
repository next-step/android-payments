package nextstep.payments.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import nextstep.payments.data.Bank
import nextstep.payments.data.bank.impl.BankRepositoryImpl

private const val COLUMN_COUNT = 4
private const val ROW_COUNT = 2

@Composable
private fun BankItem(
    bank: Bank,
    onClick: (Bank) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(75.dp)
            .clickable { onClick(bank) }
    ) {
        AsyncImage(
            model = bank.iconUrl,
            contentDescription = bank.name,
            modifier = Modifier.size(37.dp)
        )
        Text(
            text = bank.name,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 1,
            modifier = Modifier.padding(top = 10.dp)
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BankSelectRow(
    banks: List<Bank>,
    onClick: (Bank) -> Unit,
) {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 43.dp)
            .padding(top = 45.dp, bottom = 100.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalArrangement = Arrangement.spacedBy(23.dp),
        maxItemsInEachRow = COLUMN_COUNT
    ) {
        banks.take(ROW_COUNT * COLUMN_COUNT).forEach {
            BankItem(
                bank = it,
                onClick = onClick
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BackSelectRowPreview() {
    BankSelectRow(BankRepositoryImpl().getBanks()) {

    }
}
