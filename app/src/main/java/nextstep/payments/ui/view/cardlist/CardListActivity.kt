package nextstep.payments.ui.view.cardlist

import androidx.compose.runtime.Composable
import nextstep.payments.base.BaseComposeActivity
import nextstep.payments.ui.view.newcard.NewCardActivity

class CardListActivity : BaseComposeActivity() {
    override val content: @Composable () -> Unit = {
        CardListScreen(
            onShowPaymentCardDetail = {
                startActivity(NewCardActivity.newIntent(this, it))
            },
            onRegisterPaymentCard = {
                startActivity(NewCardActivity.newIntent(this))
            }
        )
    }
}
