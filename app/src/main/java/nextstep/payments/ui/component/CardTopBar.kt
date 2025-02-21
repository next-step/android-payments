package nextstep.payments.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.R
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun CardTopBar(
    isCenter: Boolean,
    modifier: Modifier = Modifier,
    onBackClick: (() -> Unit)? = null,
    onActionClick: (() -> Unit)? = null,
    actionButtonText: String? = null,
) {
    if (onActionClick != null) {
        require(actionButtonText == null) {
            "must set the buttonText"
        }
    }
    CardTopBar(
        isCenter = isCenter,
        modifier = modifier,
        onBackClick = onBackClick,
        onActionClick = onActionClick,
        actionButtonText = actionButtonText,
    )
}


@Composable
fun CardTopBar(
    isCenter: Boolean,
    modifier: Modifier = Modifier,
    onBackClick: (() -> Unit)? = null,
    onActionClick: (() -> Unit)? = null,
    actionButtonVector: ImageVector? = null,
    actionButtonContentDescription: String? = null,
) {
    if (onActionClick != null) {
        require(actionButtonVector == null) {
            "must set the buttonVector"
        }
    }

    CardTopBar(
        isCenter = isCenter,
        modifier = modifier,
        onBackClick = onBackClick,
        onActionClick = onActionClick,
        actionButtonVector = actionButtonVector,
        actionButtonContentDescription = actionButtonContentDescription,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CardTopBar(
    title: String,
    isCenter: Boolean,
    modifier: Modifier = Modifier,
    onBackClick: (() -> Unit)? = null,
    onActionClick: (() -> Unit)? = null,
    actionButtonText: String? = null,
    actionButtonVector: ImageVector? = null,
    actionButtonContentDescription: String? = null,
) {
    TopAppBar(
        modifier = modifier.fillMaxWidth(),
        title = {
            Text(
                text = title,
                modifier = Modifier.fillMaxWidth(),
                textAlign = if (isCenter) TextAlign.Center else TextAlign.Start
            )
        },
        navigationIcon = {
            TopBarIcon(
                onClick = onBackClick?.let { onBackClick },
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(R.string.back)
            )
        },
        actions = {
            actionButtonVector?.let {
                TopBarIcon(
                    onClick = onActionClick?.let { onActionClick },
                    imageVector = actionButtonVector,
                    contentDescription = actionButtonContentDescription.orEmpty(),
                )
            }
            actionButtonText?.let {
                TopBarActionText(
                    onClick = onActionClick?.let { onActionClick },
                    text = actionButtonText
                )
            }
        },
    )
}

@Composable
fun CardAddTopBar(
    onBackClick: () -> Unit,
    onCheckClick: () -> Unit,
) {
    CardTopBar(
        title = stringResource(R.string.add),
        isCenter = false,
        onActionClick = onCheckClick,
        onBackClick = onBackClick,
        actionButtonVector = Icons.Filled.Check,
        actionButtonContentDescription = stringResource(R.string.check),
    )
}

@Composable
fun CardListTopBar(
    onSaveClick: (() -> Unit)?,
) {
    CardTopBar(
        title = stringResource(R.string.payments),
        isCenter = true,
        onActionClick = onSaveClick,
        actionButtonText = stringResource(R.string.add)
    )
}

@Composable
private fun TopBarIcon(
    onClick: (() -> Unit)?,
    imageVector: ImageVector,
    contentDescription: String,
) {
    Box(
        modifier = Modifier
            .size(50.dp)
            .then(if (onClick != null) Modifier.clickable { onClick.invoke() } else Modifier),
        contentAlignment = Alignment.Center,
    ) {
        onClick?.let {
            Icon(
                imageVector = imageVector,
                contentDescription = contentDescription,
            )
        }
    }
}

@Composable
private fun TopBarActionText(
    onClick: (() -> Unit)?,
    text: String,
) {
    Box(
        modifier = Modifier
            .size(50.dp)
            .then(if (onClick != null) Modifier.clickable { onClick.invoke() } else Modifier),
        contentAlignment = Alignment.Center,
    ) {
        onClick?.let {
            Text(text = text)
        }
    }
}

@Preview
@Composable
private fun CardTopBarPreview() {
    PaymentsTheme {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            CardAddTopBar(
                onBackClick = {},
                onCheckClick = {},
            )
            CardListTopBar(
                onSaveClick = {},
            )
        }
    }
}
