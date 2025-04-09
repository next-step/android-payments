package nextstep.payments

import nextstep.payments.model.ValidationResult
import nextstep.payments.ui.newcard.NewCardInputValidator
import org.junit.Assert
import org.junit.Test

class OwnerNameValidationTest {

    @Test
    fun 카드_소유자의_이름은_30자_이하여야_한다() {
        val ownerName = "커드소유자의이름은"
        Assert.assertTrue(NewCardInputValidator.validateOwnerName(ownerName) == ValidationResult.SUCCESS)
    }

    @Test
    fun 카드_소유자의_이름이_30자를_초과하면_에러를_반환한다() {
        val ownerName = "abcdefghijklmnopqrstuvwxyzabcdefg"
        Assert.assertTrue(NewCardInputValidator.validateOwnerName(ownerName) == ValidationResult.INPUT_REJECTED)
    }
}