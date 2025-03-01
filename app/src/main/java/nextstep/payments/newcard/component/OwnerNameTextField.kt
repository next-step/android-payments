package nextstep.payments.newcard.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import nextstep.payments.R

@Composable
fun OwnerNameTextField(
    ownerName: String,
    setOwnerName: (String) -> Unit
) {
    OutlinedTextField(
        value = ownerName,
        onValueChange = setOwnerName,
        label = { Text(stringResource(R.string.card_owner_name_optional)) },
        placeholder = { Text(stringResource(R.string.input_owner_name)) },
        modifier = Modifier.fillMaxWidth(),
    )
}
