package nextstep.payments.parameters

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class BooleanPreviewParameter: PreviewParameterProvider<Boolean> {
    override val values: Sequence<Boolean> = sequenceOf(true, false)
}
