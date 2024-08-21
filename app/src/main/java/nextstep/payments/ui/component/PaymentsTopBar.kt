package nextstep.payments.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import nextstep.payments.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentsTopBar(
    title: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    onActionClick: (() -> Unit)? = null,
    actionContentDescription: String? = null,
) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = { onBackClick() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.top_bar_back_content_description),
                )
            }
        },
        actions = {
            if (onActionClick != null) {
                IconButton(onClick = { onActionClick() }) {
                    Icon(
                        imageVector = Icons.Filled.Check,
                        contentDescription = actionContentDescription,
                    )
                }
            }
        },
        modifier = modifier,
    )
}
