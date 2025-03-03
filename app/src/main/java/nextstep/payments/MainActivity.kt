package nextstep.payments

import androidx.compose.runtime.Composable
import nextstep.payments.base.BaseComponentActivity
import nextstep.payments.ui.main.MainScreen

class MainActivity : BaseComponentActivity() {
    @Composable
    override fun SetContent() {
        MainScreen()
    }
}
