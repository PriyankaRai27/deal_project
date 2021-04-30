package com.target.targetcasestudy

import com.target.targetcasestudy.model.Validators
import org.junit.Assert
import org.junit.Test
import javax.xml.validation.Validator

/**
 * Feel free to make modifications to these unit tests! Remember, you have full technical control
 * over the project, so you can use any libraries and testing strategies that see fit.
 */
class CreditCardValidatorTest {
  @Test
  fun `is credit card number valid`() {
    Assert.assertTrue(
      "valid credit card number should yield true",
      Validators().validateCreditCard("4485326207989867954")
    )
  }

  @Test
  fun `is credit card number not valid`() {
    Assert.assertFalse(
      "valid credit card number should yield true",
      Validators().validateCreditCard("448532620798986795")
    )
  }
}
