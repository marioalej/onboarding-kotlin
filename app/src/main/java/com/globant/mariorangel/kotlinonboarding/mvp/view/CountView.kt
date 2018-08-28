package com.globant.mariorangel.kotlinonboarding.mvp.view

import android.app.Activity
import com.globant.mariorangel.kotlinonboarding.util.bus.Constants
import com.globant.mariorangel.kotlinonboarding.util.bus.RxBus
import com.globant.mariorangel.kotlinonboarding.util.bus.calculator.*
import kotlinx.android.synthetic.main.activity_main.*

class CountView(activity: Activity) : ActivityView(activity) {

    init {

        activity.plus_button.setOnClickListener {
            RxBus.post(OperatorButtonPressedObserver.OnButtonPressed(
                    activity.count_input.text.toString(),
                    Constants.PLUS
            ))
        }

        activity.minus_button.setOnClickListener {
            RxBus.post(OperatorButtonPressedObserver.OnButtonPressed(
                    activity.count_input.text.toString(),
                    Constants.MINUS
            ))
        }

        activity.multiplier_button.setOnClickListener {
            RxBus.post(OperatorButtonPressedObserver.OnButtonPressed(
                    activity.count_input.text.toString(),
                    Constants.MULTIPLIER
            ))
        }

        activity.divider_button.setOnClickListener {
            RxBus.post(OperatorButtonPressedObserver.OnButtonPressed(
                    activity.count_input.text.toString(),
                    Constants.DIVIDER
            ))
        }

        activity.equal_button.setOnClickListener {
            RxBus.post(EqualButtonPressedObserver.OnEqualButtonPressed(
                    activity.count_input.text.toString()
            ))
        }

        activity.clear_button.setOnClickListener {
            activity.count_result.text = Constants.SPACE
            it.isEnabled = false
        }
    }

    fun cleanInput() {
        activity!!.count_input.setText(Constants.SPACE)
    }

    /**
     * will update the view with the operation result
     */
    fun setResult(result: String) {
        activity!!.count_result.text = result
        activity!!.clear_button.isEnabled = true
    }

}