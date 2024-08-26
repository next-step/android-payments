package nextstep.payments.component.topbar

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import nextstep.payments.R
import nextstep.payments.ui.theme.PaymentsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardListTopBar(
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
    isShownAddText : Boolean = false
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
            )
        },
        actions = {
            if(isShownAddText){
                TextButton(
                    onClick = onSaveClick
                ) {
                    Text(
                        text = stringResource(id = R.string.card_list_add),
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Black
                    )
                }
            }

        },
        modifier = modifier
    )
}

@Preview(showBackground = true, name = "CardListTopBar")
@Composable
private fun Preview1() {
    PaymentsTheme {
        CardListTopBar(onSaveClick = { })
    }
}

@Preview(showBackground = true, name = "CardListTopBarWithAddText")
@Composable
private fun Preview2() {
    PaymentsTheme {
        CardListTopBar(
            onSaveClick = { },
            isShownAddText = true
        )
    }
}

