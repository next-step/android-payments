package nextstep.payments.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import nextstep.payments.R
import nextstep.payments.model.BankType
import nextstep.payments.ui.theme.BankTextColor


@Composable
fun BankSelectorItem(
    bankType: BankType,
    onClickBankType: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.clickable {
            onClickBankType()
        },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = bankType.url,
            contentDescription = "",
            modifier = Modifier.size(37.dp),
            contentScale = ContentScale.Fit,
            placeholder = painterResource(id = R.drawable.ic_launcher_background)
        )
        Spacer(modifier = Modifier.height(9.dp))
        Text(
            text = bankType.companyName,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            color = BankTextColor
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BackSelectorItemPreview() {
    BankSelectorItem(
        bankType = BankType.BC,
        onClickBankType = { }
    )
}
