package nextstep.payments.newcard.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.model.BankType
import nextstep.payments.newcard.NewCardEvent
import nextstep.payments.newcard.model.BankTypeButtonUiModel
import nextstep.payments.ui.theme.PaymentsTheme
import nextstep.payments.ui.theme.TypoTokens.Medium16

@Composable
fun BankSelectButton(
    bankTypeButtonUiModel: BankTypeButtonUiModel,
    sendEvent: (NewCardEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        Box(
            modifier = Modifier
                .size(37.dp)
                .clip(CircleShape)
                .clickable { sendEvent(NewCardEvent.OnClickBankSelectButton(bankTypeButtonUiModel.bankType)) }
        ) {
            Image(
                painter = painterResource(id = bankTypeButtonUiModel.iconResId),
                contentDescription = bankTypeButtonUiModel.title,
            )
        }
        Spacer(modifier = Modifier.height(11.dp))
        Text(
            text = bankTypeButtonUiModel.title,
            style = Medium16,
        )
    }
}

@Preview
@Composable
private fun BankItemPreview() {
    PaymentsTheme {
        BankSelectButton(
            bankTypeButtonUiModel = BankTypeButtonUiModel.from(BankType.KB),
            sendEvent = {},
        )
    }
}

