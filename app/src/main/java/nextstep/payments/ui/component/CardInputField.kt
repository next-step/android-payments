package nextstep.payments.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.data.model.CardInputType

@Composable
internal fun CardInputField(
    value: String,
    onValueChange: (String) -> Unit,
    labelText: String,
    placeHolderText: String,
    type: CardInputType,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = value,
        onValueChange = { it -> onValueChange(it.take(type.maxLength)) },
        label = { Text(labelText) },
        placeholder = { Text(placeHolderText) },
        modifier = modifier.fillMaxWidth(),
        singleLine = true,
        visualTransformation = type.visualTransformation,
        keyboardOptions = type.keyboardOptions,
    )
}

@Preview(showBackground = true)
@Composable
private fun CardInputFiledPreview() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        var cardNumber by remember { mutableStateOf("") }
        var name by remember { mutableStateOf("") }
        var expiredDate by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }


        CardInputField(
            value = cardNumber.take(16),
            onValueChange = { cardNumber = it },
            labelText = "카드번호",
            placeHolderText = "0000-0000-0000-0000",
            type = CardInputType.CardNumber,
        )

        CardInputField(
            value = expiredDate.take(4),
            onValueChange = { expiredDate = it },
            labelText = "만료일",
            placeHolderText = "MM/YY",
            type = CardInputType.ExpiredDate,
        )

        CardInputField(
            value = name,
            onValueChange = { name = it },
            labelText = "이름 (선택)",
            placeHolderText = "카드 소유자",
            type = CardInputType.OwnerName,
        )

        CardInputField(
            value = password.take(4),
            onValueChange = { password = it },
            labelText = "비밀번호",
            placeHolderText = "비밀번호",
            type = CardInputType.Password,
        )
    }
}