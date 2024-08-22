package nextstep.payments.ui.view.cardlist

import androidx.compose.runtime.Composable
import nextstep.payments.base.BaseComposeActivity

class CardListActivity : BaseComposeActivity() {
    override val content: @Composable () -> Unit = {
        CardListScreen(
            onShowPaymentCardDetail = {
                // TODO: 결제카드 상세로 이동
            },
            onRegisterPaymentCard = {
                // TODO: 결제카드 등록으로 이동
            }
        )
    }
}
