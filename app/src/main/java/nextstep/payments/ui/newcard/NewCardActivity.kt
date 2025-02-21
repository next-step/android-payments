package nextstep.payments.ui.newcard

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import nextstep.payments.base.BaseComponentActivity

class NewCardActivity : BaseComponentActivity() {
    @Composable
    override fun SetContent() {
        NewCardScreen(
            onRouteToCardList = {
                setResult(RESULT_OK)
                finish()
            },
            onBackClick = { finish() }
        )
    }
    companion object {
        fun newInstance(context: Context): Intent {
            return Intent(context, NewCardActivity::class.java)
        }
    }
}