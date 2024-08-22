package nextstep.payments.ui.view.newcard

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.core.os.bundleOf
import nextstep.payments.base.BaseComposeActivity
import nextstep.payments.model.PaymentCardModel

class NewCardActivity : BaseComposeActivity() {
    override val content: @Composable () -> Unit = {
        NewCardScreen()
    }

    companion object {
        const val EXTRA_PAYMENT_CARD = "paymentCard"

        fun newIntent(
            context: Context,
            paymentCard: PaymentCardModel? = null,
        ): Intent {
            return Intent(context, NewCardActivity::class.java).apply {
                putExtras(
                    bundleOf(
                        EXTRA_PAYMENT_CARD to paymentCard
                    )
                )
            }
        }
    }
}
