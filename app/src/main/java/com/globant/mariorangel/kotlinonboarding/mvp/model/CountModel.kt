package com.globant.mariorangel.kotlinonboarding.mvp.model

class CountModel {

    private var number = 0.0

    /**
     * To start, this function will initialize the operation, settings the first
     * value to the operation, it will be setting the number back inside the listener
     * <p>
     * If the number cannot be parsed, it will return a null into the listener
     *
     * @param value first num to be performed
     * @param listener  listener that will be notifying action result
     */
    fun addFirstNumber(value: String, listener: (Double?) -> Unit) {
        try {
            number = value.toDouble()
            listener(number)
        } catch (e: NumberFormatException) {
            listener(null)
        }
    }

    /**
     * Here, the operation is done.
     * Following functions will be doing the operations returnig the
     * result through the listener
     *
     * @param value second value from the EditText
     * @param listener  listener that will notify the result of the operation
     */
    fun addition(value: String, listener: (Double?) -> Unit) {

        try {
            val value1 = value.toDouble()

            listener(number + value1)
        } catch (e: NumberFormatException) {
            listener(null)
        }
    }

    fun subtraction(value: String, listener: (Double?) -> Unit) {
        try {
            val value1 = value.toDouble()

            listener(number - value1)

        } catch (e: NumberFormatException) {
            listener(null)
        }
    }

    fun multiplication(value: String, listener: (Double?) -> Unit) {
        try {
            val value1 = value.toDouble()

            listener(number * value1)

        } catch (e: NumberFormatException) {
            listener(null)
        }
    }

    fun division(value: String, listener: (Double?) -> Unit) {
        try {
            val value1 = value.toDouble()

            listener(number / value1)

        } catch (e: NumberFormatException) {
            listener(null)
        }
    }
}