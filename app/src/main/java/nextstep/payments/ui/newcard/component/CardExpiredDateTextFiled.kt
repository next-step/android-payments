package nextstep.payments.ui.newcard.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import nextstep.payments.R
import nextstep.payments.designsystem.transformed.expiredDateTransformedText

@Composable
fun CardExpiredDateTextFiled(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        visualTransformation = { expiredDateTransformedText(it) },
        label = { Text(stringResource(R.string.text_filed_label_expired_date)) },
        placeholder = { Text(stringResource(R.string.text_filed_placeholder_expired_date)) },
        modifier = modifier
            .fillMaxWidth()
            .semantics {
                contentDescription = "CardExpiredDateTextFiled"
            },
    )
}

@Preview(showBackground = true)
@Composable
private fun ExpiredDateTextFiledPreview() {
    var input = remember { mutableStateOf<String>("") }

    CardExpiredDateTextFiled(
        value = input.value,
        onValueChange = {
            input.value = it.take(4)
        }
    )
}
