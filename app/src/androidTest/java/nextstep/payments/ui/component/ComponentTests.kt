package nextstep.payments.ui.component

import nextstep.payments.ui.screen.creditcard.CreditCardScreenTest
import nextstep.payments.ui.screen.newcard.NewCardScreenTest
import org.junit.runner.RunWith
import org.junit.runners.Suite
import org.junit.runners.Suite.SuiteClasses

@RunWith(Suite::class)
@SuiteClasses(
    PaymentCardTest::class,
)
internal class ComponentTests
