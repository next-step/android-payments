package nextstep.payments.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.ui.newcard.model.BankUI

private const val COLUMN_COUNT = 4

@SuppressLint("ResourceType")
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BankSelectRow(
    banks: List<BankUI>,
    onBankSelect: (BankUI) -> Unit,
    modifier: Modifier = Modifier
) {
    FlowRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                vertical = 36.dp,
                horizontal = 43.dp
            ),
        maxItemsInEachRow = COLUMN_COUNT,
        verticalArrangement = Arrangement.spacedBy(23.dp),
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        banks.forEach { bankUI ->
            Column(
                modifier = Modifier
                    .weight(1f)
                    .clickable { onBankSelect(bankUI) },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = bankUI.imageResId),
                    contentDescription = bankUI.bankName,
                    modifier = Modifier.size(37.dp)
                )

                Spacer(modifier = Modifier.padding(vertical = 8.dp))

                Text(
                    text = bankUI.bankName,
                    style = MaterialTheme.typography.titleMedium
                )
            }

        }
    }
}

@Preview
@Composable
private fun BankSelectRowPreview() {
    BankSelectRow(
        banks = BankUI.entries,
        onBankSelect = {}
    )
}
