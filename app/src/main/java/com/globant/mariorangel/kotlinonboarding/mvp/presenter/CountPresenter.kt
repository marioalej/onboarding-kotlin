package com.globant.mariorangel.kotlinonboarding.mvp.presenter

import android.app.Activity
import com.globant.mariorangel.kotlinonboarding.mvp.model.CountModel
import com.globant.mariorangel.kotlinonboarding.mvp.view.CountView
import com.globant.mariorangel.kotlinonboarding.util.bus.RxBus
import com.globant.mariorangel.kotlinonboarding.util.bus.calculator.EqualButtonPressedObserver
import com.globant.mariorangel.kotlinonboarding.util.bus.calculator.OperatorButtonPressedObserver

class CountPresenter(private val model: CountModel,
                     private val view: CountView) {

    //It's whom determine the operation inside the presenter
    private var operation = CountView.SPACE

    //Bind observers to Activity
    fun register() {

        val activity = view.activity

        if (activity != null) subscribeObservers(activity)
    }

    //Unbind observers to Activity
    fun unRegister() {
        val activity = view.activity ?: return

        RxBus.clear(activity)
    }

    // subscribe listeners to the different operations
    private fun subscribeObservers(activity: Activity) {

        RxBus.subscribe(activity, object : OperatorButtonPressedObserver() {
            override fun onEvent(value: OnButtonPressed) {
                model.addNumber(value.value.toDouble())
                operation = value.operator
                view.cleanInput()
            }
        })

        RxBus.subscribe(activity, object : EqualButtonPressedObserver() {
            override fun onEvent(value: OnEqualButtonPressed) {
                makeOperation(value.value.toDouble())
            }
        })
    }

    private fun makeOperation(value: Double) {
        when (operation) {
            CountView.PLUS -> model.addition(value) { updateResult(it) }
            CountView.MINUS -> model.subtraction(value) { updateResult(it) }
            CountView.MULTIPLIER -> model.multiplication(value) { updateResult(it) }
            CountView.DIVIDER -> model.division(value) { updateResult(it) }
            CountView.SPACE -> updateResult(value)
        }
    }

    /**
     * This function is going to handle the operation result and
     * will notify to the view when is done
     *
     * @param result value from EditText
     */
    private fun updateResult(result: Double) {
        view.setResult(result.toString())
        view.cleanInput()
        operation = CountView.SPACE
    }

}