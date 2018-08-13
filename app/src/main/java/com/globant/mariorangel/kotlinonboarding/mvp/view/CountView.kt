package com.globant.mariorangel.kotlinonboarding.mvp.view

import android.app.Activity
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import com.globant.mariorangel.kotlinonboarding.R
import com.globant.mariorangel.kotlinonboarding.util.bus.RxBus
import com.globant.mariorangel.kotlinonboarding.util.bus.calculator.EditTextChangedObserver
import kotlinx.android.synthetic.main.activity_main.*

class CountView : ActivityView {

    /**
     * I've replaced `init` constructor to this to keep the activity reference as
     * a property of this class
     */
    constructor(activity: Activity) : super(activity) {

        // Setted TextWatcher listener
        activity.count_input.addTextChangedListener(PlusWatcher())

        // Setted reset onClickListener
        activity.clear_button.setOnClickListener {
            activity.count_result.text = BLANK_SPACE
            it.isEnabled = false
        }
    }

    // Will update the view
    fun setResult(result: String) {
        activity!!.count_result.text = result
        activity!!.clear_button.isEnabled = true
    }

    // will show a toast if user insert a word
    fun fireToastWithIncorrectFormat() {
        Toast.makeText(context, R.string.incorrect_format, Toast.LENGTH_SHORT).show()
    }

    /**
     * inner class that listen the user input inside the editText.
     * It'll post into the bus when user inputs the plus sign. In this order,
     * the operation will be done when user insert a second plus sign after inserting
     * the second value
     */
    private inner class PlusWatcher: TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            val string = p0.toString()

            if(string.takeLast(1) == PLUS) {
                RxBus.post(EditTextChangedObserver.OnTextChanged(string.takeWhile { it.toString() != PLUS }))
                activity!!.count_input.setText(BLANK_SPACE)
            }
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }
    }

    companion object {
        private const val BLANK_SPACE = ""
        private const val PLUS = "+"
    }

}