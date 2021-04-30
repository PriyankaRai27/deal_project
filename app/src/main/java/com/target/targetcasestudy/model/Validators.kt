package com.target.targetcasestudy.model

/**
 * For an explanation of how to validate credit card numbers read:
 *
 * https://www.freeformatter.com/credit-card-number-generator-validator.html#fakeNumbers
 *
 * This contains a breakdown of how this algorithm should work as
 * well as a way to generate fake credit card numbers for testing
 *
 * The structure and signature of this is open to modification, however
 * it *must* include a method, field, etc that returns a [Boolean]
 * indicating if the input is valid or not
 *
 * Additional notes:
 *  * This method does not need to validate the credit card issuer
 *  * This method must validate card number length (13 - 19 digits), but does not
 *    need to validate the length based on the issuer.
 *
 * @param creditCardNumber - credit card number of (13, 19) digits
 * @return true if a credit card number is believed to be valid,
 * otherwise false
 */


class Validators {
    fun validateCreditCard(creditCardNumber: String): Boolean {
        return isValid(creditCardNumber)
    }

    // Return true if the card number is valid
    private fun isValid(cardNumber: String): Boolean {
        var isValid: Boolean = false
        if (cardNumber != "") {
            val creditCardNumber = cardNumber.toLong()
            val lastDigit = cardNumber.substring(cardNumber.length - 1)
            isValid = (getSize(creditCardNumber) in 13..19 &&
                    (prefixMatched(creditCardNumber, 4) ||
                            prefixMatched(creditCardNumber, 5) ||
                            prefixMatched(creditCardNumber, 3) ||
                            prefixMatched(creditCardNumber, 6)) &&
                    (sumOfDoubleEvenPlace(creditCardNumber) +
                            sumOfOddPlace(creditCardNumber) + lastDigit.toInt()) % 10 == 0)
        }
        return isValid
    }

    // Get the result from Step 2
    fun sumOfDoubleEvenPlace(number: Long): Int {
        var sum = 0
        val num = number.toString() + ""
        var i = getSize(number) - 2
        while (i >= 0) {
            sum += getDigit((num[i].toString() + "").toInt() * 2)
            i -= 2
        }
        return sum
    }

    // Return this number if it is a single digit, otherwise,subtract 9
    fun getDigit(number: Int): Int {
        return if (number < 9) number else number - 9
    }

    // Return sum of odd-place digits in number
    fun sumOfOddPlace(number: Long): Int {
        var sum = 0
        val num = number.toString() + ""
        var i = getSize(number) - 3
        while (i >= 0) {
            sum += (num[i].toString() + "").toInt()
            i -= 2
        }
        return sum
    }

    // Return true if the digit d is a prefix for number
    private fun prefixMatched(number: Long, d: Int): Boolean {
        return getPrefix(number, getSize(d.toLong())) == d.toLong()
    }

    // Return the number of digits in d
    private fun getSize(d: Long): Int {
        val num = d.toString() + ""
        return num.length
    }

    private fun getPrefix(number: Long, k: Int): Long {
        if (getSize(number) > k) {
            val num = number.toString() + ""
            return num.substring(0, k).toLong()
        }
        return number
    }
}