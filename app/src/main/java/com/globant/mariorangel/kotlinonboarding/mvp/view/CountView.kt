package com.globant.mariorangel.kotlinonboarding.mvp.view

import android.app.Activity
import com.globant.mariorangel.kotlinonboarding.mvp.model.CountModel
import com.globant.mariorangel.kotlinonboarding.util.bus.RxBus
import com.globant.mariorangel.kotlinonboarding.util.bus.calculator.*
import kotlinx.android.synthetic.main.activity_main.*

class CountView(activity: Activity) : ActivityView(activity) {

    init {

        activity.plus_button.setOnClickListener {
            RxBus.post(OperatorButtonPressedObserver.OnButtonPressed(
                    activity.count_input.text.toString(),
                    CountModel.PLUS
            ))
        }

        activity.minus_button.setOnClickListener {
            RxBus.post(OperatorButtonPressedObserver.OnButtonPressed(
                    activity.count_input.text.toString(),
                    CountModel.MINUS
            ))
        }

        activity.multiplier_button.setOnClickListener {
            RxBus.post(OperatorButtonPressedObserver.OnButtonPressed(
                    activity.count_input.text.toString(),
                    CountModel.MULTIPLIER
            ))
        }

        activity.divider_button.setOnClickListener {
            RxBus.post(OperatorButtonPressedObserver.OnButtonPressed(
                    activity.count_input.text.toString(),
                    CountModel.DIVIDER
            ))
        }

        activity.equal_button.setOnClickListener {
            RxBus.post(EqualButtonPressedObserver.OnEqualButtonPressed(
                    activity.count_input.text.toString()
            ))
        }

        activity.clear_button.setOnClickListener {
            activity.count_result.text = CountModel.SPACE
            it.isEnabled = false
        }
    }

    fun cleanInput() {
        activity!!.count_input.setText(CountModel.SPACE)
    }

    /**
     * will update the view with the operation result
     */
    fun setResult(result: String) {
        activity!!.count_result.text = result
        activity!!.clear_button.isEnabled = true
    }

}