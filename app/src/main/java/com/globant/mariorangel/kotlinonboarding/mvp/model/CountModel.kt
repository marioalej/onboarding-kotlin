package com.globant.mariorangel.kotlinonboarding.mvp.model

import java.lang.Integer.parseInt

class CountModel {

    private var number: Int? = null

    /**
     * Here, the operation is done. To start, the function is
     * going to ask if the first number is already setted, if it's not
     * It will set in the listener 0 <Zero> and save the value for the
     * first number.
     * <p>
     * if it's, then it will set in the listener the result of the
     * operation.
     *
     * @param value value from the EditText
     * @param listener  listener that will handle the result of the operation
     */
    fun plus(value: String, listener: (Int) -> Unit) {

        try {
            val value1 = value.toInt()

            if (number == null) {
                number = value1
                listener(0)
            } else {
                val result = number!! + value1
                number = null
                listener(result)
            }
        } catch (e: NumberFormatException) {
            listener(-1)
        }


    }
}