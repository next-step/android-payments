package nextstep.payments.feature.cardlist.view

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardListTopBar(
    showAddButton: Boolean,
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text("Payments")
        },
        actions = {
            if (showAddButton) {
                IconButton(
                    onClick = { onAddClick() },
                    modifier = Modifier.testTag("AddNewCardText")
                ) {
                    Text(
                        text = "추가",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }
            }
        }
    )
}