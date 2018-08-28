package com.globant.mariorangel.kotlinonboarding.mvp.model

class CountModel {

    var number = 0.0
        private set
    lateinit var operation: String
        private set

    fun addNumber(value: Double) {
        number = value
    }

    fun defineOperator(operator: String) {
        operation = operator
    }

    /**
     * Here, the operation is done.
     * Following functions will be doing the operations returnig the
     * result through the listener
     *
     * @param value second value from the EditText
     * @param listener  listener that will notify the result of the operation
     */
    fun addition(value: Double, listener: (Double) -> Unit) {
        listener(number + value)
    }

    fun subtraction(value: Double, listener: (Double) -> Unit) {
        listener(number - value)
    }

    fun multiplication(value: Double, listener: (Double) -> Unit) {
        listener(number * value)
    }

    fun division(value: Double, listener: (Double) -> Unit) {
        listener(number / value)
    }


}