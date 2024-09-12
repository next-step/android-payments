package nextstep.payments.component.topbar

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
import androidx.compose.ui.platform.testTag
import nextstep.payments.screen.model.ManageCardType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManageCardTopBar(
    manageCardType: ManageCardType,
    isSaveCardEnabled: Boolean,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = {
            Text(
                text = when (manageCardType) {
                    ManageCardType.ADD -> "카드 추가"
                    ManageCardType.EDIT -> "카드 수정"
                }
            )
        },
        navigationIcon = {
            IconButton(onClick = { onBackClick() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "뒤로 가기",
                )
            }
        },
        actions = {
            IconButton(
                modifier = Modifier.testTag("saveButton"),
                enabled = isSaveCardEnabled,
                onClick = { onSaveClick() }
            ) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = "완료",
                )
            }
        },
        modifier = modifier
    )
}
