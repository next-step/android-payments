package nextstep.payments.ui.newcard.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.payments.designsystem.theme.BankNameColor
import nextstep.payments.designsystem.theme.PaymentsTheme
import nextstep.payments.ext.setContentDescription
import nextstep.payments.model.BankType

@Composable
fun BankSelectItem(
    item: BankType,
    onItemClick: (BankType) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .width(69.dp)
            .clickable(onClick = { onItemClick(item) }),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Image(
            painter = painterResource(item.image),
            contentDescription = "${item.bankName}",
            modifier = Modifier.size(37.dp).setContentDescription("${item.image}")
        )
        Text(
            stringResource(item.bankName),
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = BankNameColor
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BankSelectItemPreview() {
    PaymentsTheme {
        BankSelectItem(
            item = BankType.HYUNDAI,
            onItemClick = {}
        )
    }
}