package com.globant.mariorangel.kotlinonboarding.mvp.view

import android.app.Activity
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import com.globant.mariorangel.kotlinonboarding.util.bus.RxBus
import com.globant.mariorangel.kotlinonboarding.util.bus.calculator.*
import kotlinx.android.synthetic.main.activity_main.*

class CountView(activity: Activity) : ActivityView(activity) {

    init {
        //Setup TextWatcher listener
        activity.count_input.addTextChangedListener(PlusWatcher())
        //Setup of clear button listener
        activity.clear_button.setOnClickListener {
            cleanResultLabel()
            cleanNumberLabel()
            it.isEnabled = false
        }
    }

    fun cleanInput() {
        activity!!.count_input.setText(BLANK_SPACE)
    }

    fun cleanNumberLabel() {
        activity!!.operation_label.text = BLANK_SPACE
    }

    fun cleanResultLabel() {
        activity!!.count_result.text = BLANK_SPACE
    }

    /**
     * It will be printing the first number inserted by the user
     */
    fun updateLabel(s: String) {
        activity!!.operation_label.text = s
    }

    /**
     * will update the view with the operation result
     */
    fun setResult(result: String) {
        activity!!.count_result.text = result
        activity!!.clear_button.isEnabled = true
    }

    // will show a toast if user insert a word
    fun fireShortToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    /**
     * TextWatcher inner class that listen user input inside the editText.
     * It'll post to the bus when user insert something on the EditText, if the first input
     * is something different of a number, it will trigger a toast displaying a incorrect number format message.
     * <p>
     * If the second input is different of an operator, it will erase the first number if input it's a number
     * and will save the second one.
     * <p>
     * In this order, the operation will be done when user insert the equal sign after inserting
     * the second value
     */
    private inner class PlusWatcher: TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            val string = p0.toString()

            when (string.takeLast(1)) {
                PLUS -> {
                    RxBus.post(TextEditObserver.OnKeyPressed(string.takeWhile { it.toString() != PLUS }, PLUS))
                    cleanInput()
                }
                MINUS -> {
                    RxBus.post(TextEditObserver.OnKeyPressed(string.takeWhile { it.toString() != MINUS }, MINUS))
                    cleanInput()
                }
                MULTIPLIER -> {
                    RxBus.post(TextEditObserver.OnKeyPressed(string.takeWhile { it.toString() != MULTIPLIER }, MULTIPLIER))
                    cleanInput()
                }
                DIVIDER -> {
                    RxBus.post(TextEditObserver.OnKeyPressed(string.takeWhile { it.toString() != DIVIDER }, DIVIDER))
                    cleanInput()
                }
                EQUAL -> {
                    RxBus.post(EqualPressedObserver.OnEqualPressed(string.takeWhile { it.toString() != EQUAL }))
                }
            }
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }
    }

    companion object {
        const val BLANK_SPACE = ""
        const val PLUS = "+"
        const val MINUS = "-"
        const val MULTIPLIER =  "*"
        const val DIVIDER = "/"
        const val EQUAL = "="
    }

}