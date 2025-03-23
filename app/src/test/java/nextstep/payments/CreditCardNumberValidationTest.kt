package nextstep.payments

import nextstep.payments.model.ValidationResult
import nextstep.payments.ui.newcard.NewCardInputValidator
import org.junit.Assert
import org.junit.Test

class CreditCardNumberValidationTest {

    @Test
    fun 카드_번호는_숫자만_포함되어야_한다() {
        val cardNumber = "1234567812345678"
        Assert.assertTrue(NewCardInputValidator.validateCardNumber(cardNumber) == ValidationResult.SUCCESS)
    }

    @Test
    fun 카드_번호에_숫자_이외의_문자가_포함되면_에러가_반환된다() {
        var cardNumber = "1234-5678-1234-5"
        Assert.assertTrue(NewCardInputValidator.validateCardNumber(cardNumber) == ValidationResult.INPUT_REJECTED)
    }

    @Test
    fun 카드_번호는_16자리_숫자여야_한다() {
        var cardNumber = "1234567812345678"
        Assert.assertTrue(NewCardInputValidator.validateCardNumber(cardNumber) == ValidationResult.SUCCESS)
    }

    @Test
    fun 카드_번호가_16자리가_아니면_에러가_반환된다() {
        // 16자리보다 짧은 경우
        var cardNumber = "12345678123456"
        Assert.assertTrue(NewCardInputValidator.validateCardNumber(cardNumber) == ValidationResult.ADDITIONAL_INPUT_REQUIRED)

        // 16자리보다 긴 경우
        cardNumber = "123456781234567812"
        Assert.assertTrue(NewCardInputValidator.validateCardNumber(cardNumber) == ValidationResult.INPUT_REJECTED)
    }
}