package com.globant.mariorangel.kotlinonboarding.mvp.view

import android.app.Activity
import com.globant.mariorangel.kotlinonboarding.util.bus.RxBus
import com.globant.mariorangel.kotlinonboarding.util.bus.calculator.*
import kotlinx.android.synthetic.main.activity_main.*

class CountView(activity: Activity) : ActivityView(activity) {

    init {

        activity.plus_button.setOnClickListener {
            if (!activity.count_input.text.isEmpty())
                RxBus.post(OperatorButtonPressedObserver.OnButtonPressed(
                        activity.count_input.text.toString(),
                        PLUS
                ))
        }

        activity.minus_button.setOnClickListener {
            if (!activity.count_input.text.isEmpty())
                RxBus.post(OperatorButtonPressedObserver.OnButtonPressed(
                        activity.count_input.text.toString(),
                        MINUS
                ))
        }

        activity.multiplier_button.setOnClickListener {
            if (!activity.count_input.text.isEmpty())
                RxBus.post(OperatorButtonPressedObserver.OnButtonPressed(
                        activity.count_input.text.toString(),
                        MULTIPLIER
                ))
        }

        activity.divider_button.setOnClickListener {
            if (!activity.count_input.text.isEmpty())
                RxBus.post(OperatorButtonPressedObserver.OnButtonPressed(
                        activity.count_input.text.toString(),
                        DIVIDER
                ))
        }

        activity.equal_button.setOnClickListener {
            if (!activity.count_input.text.isEmpty())
                RxBus.post(EqualButtonPressedObserver.OnEqualButtonPressed(
                        activity.count_input.text.toString()
                ))
        }

        activity.clear_button.setOnClickListener {
            activity.count_result.text = SPACE
            it.isEnabled = false
        }
    }

    fun cleanInput() {
        activity!!.count_input.setText(SPACE)
    }

    /**
     * will update the view with the operation result
     */
    fun setResult(result: String) {
        activity!!.count_result.text = result
        activity!!.clear_button.isEnabled = true
    }

    companion object {
        const val SPACE = ""
        const val PLUS = "+"
        const val MINUS = "-"
        const val MULTIPLIER =  "*"
        const val DIVIDER = "/"
    }

}