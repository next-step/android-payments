package nextstep.payments.ui.card.list.component

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import nextstep.payments.R

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CardListTopBar(
    displayAdd: Boolean,
    onShowNewCard: () -> Unit,
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        title = { Text(text = stringResource(id = R.string.payments)) },
        actions = {
            if (displayAdd) {
                TextButton(onClick = onShowNewCard) {
                    Text(
                        text = stringResource(id = R.string.add),
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        },
        modifier = modifier,
    )
}
