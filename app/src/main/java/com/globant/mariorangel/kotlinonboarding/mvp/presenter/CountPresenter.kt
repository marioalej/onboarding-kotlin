package com.globant.mariorangel.kotlinonboarding.mvp.presenter

import com.globant.mariorangel.kotlinonboarding.mvp.model.CountModel
import com.globant.mariorangel.kotlinonboarding.mvp.view.CountView
import com.globant.mariorangel.kotlinonboarding.util.bus.OnCountButtonPressedBusObserver
import com.globant.mariorangel.kotlinonboarding.util.bus.OnResetButtonPressedBusObserver
import com.globant.mariorangel.kotlinonboarding.util.bus.RxBus

class CountPresenter(model: CountModel, view: CountView) {

    init {

        val activity = view.activity
        if (activity != null) {

            RxBus.subscribe(activity, object : OnCountButtonPressedBusObserver() {
                override fun onEvent(value: OnCountButtonPressedBusObserver.OnCountButtonPressed) {
                    model.inc()
                    view.setCount(model.count.toString())
                }
            })

            RxBus.subscribe(activity, object : OnResetButtonPressedBusObserver() {
                override fun onEvent(value: OnResetButtonPressedBusObserver.OnResetButtonPressed) {
                    model.reset()
                    view.setCount(model.count.toString())
                }
            })
        }

    }
}