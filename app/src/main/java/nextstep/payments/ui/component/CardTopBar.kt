package nextstep.payments.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.R
import nextstep.payments.ui.theme.PaymentsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardTopBar(
    title: String,
    isCenter: Boolean,
    modifier: Modifier = Modifier,
    onSaveClick: (() -> Unit)? = null,
    showBackButton: Boolean = false,
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                modifier = Modifier.fillMaxWidth(),
                textAlign = if (isCenter) TextAlign.Center else TextAlign.Start
            )
        },
        navigationIcon = {
            Box(
                modifier = Modifier.size(50.dp)
            ) {
                if (showBackButton) {
                    IconButton(onClick = {
                        // todo
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back),
                        )
                    }
                }
            }
        },
        actions = {
            Box(
                modifier = Modifier.size(50.dp)
            ) {
                onSaveClick?.let {
                    IconButton(onClick = { it.invoke() }) {
                        Icon(
                            imageVector = Icons.Filled.Check,
                            contentDescription = stringResource(R.string.complete),
                        )
                    }
                }
            }
        },
        modifier = modifier.fillMaxWidth()
    )
}

private val topBarHeight = 64.dp

@Preview
@Composable
private fun CardTopBarPreview() {
    PaymentsTheme {
        CardTopBar(
            title = "안녕",
            isCenter = true,
        )
    }
}
