package com.globant.mariorangel.kotlinonboarding.mvp.view

import android.app.Activity
import com.globant.mariorangel.kotlinonboarding.util.bus.OnCountButtonPressedBusObserver
import com.globant.mariorangel.kotlinonboarding.util.bus.OnResetButtonPressedBusObserver
import com.globant.mariorangel.kotlinonboarding.util.bus.RxBus
import kotlinx.android.synthetic.main.activity_main.*

class CountView(activity: Activity) : ActivityView(activity) {

    init {
        activity.count_button.setOnClickListener {
            RxBus.post(OnCountButtonPressedBusObserver.OnCountButtonPressed())
        }

        activity.reset_button.setOnClickListener {
            RxBus.post(OnResetButtonPressedBusObserver.OnResetButtonPressed())
        }
    }

    fun setCount(count: String) {
        activity!!.count_label.text = count
    }
}