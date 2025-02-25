package nextstep.payments.component.topbar

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardListTopBar(
    rightButton: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = { Text("Payments") },
        actions = {
            rightButton()
        },
        modifier = modifier
    )
}
