package com.globant.mariorangel.kotlinonboarding.mvp.presenter

import android.app.Activity
import com.globant.mariorangel.kotlinonboarding.mvp.model.CountModel
import com.globant.mariorangel.kotlinonboarding.mvp.view.CountView
import com.globant.mariorangel.kotlinonboarding.util.bus.RxBus
import com.globant.mariorangel.kotlinonboarding.util.bus.calculator.EqualButtonPressedObserver
import com.globant.mariorangel.kotlinonboarding.util.bus.calculator.OperatorButtonPressedObserver

class CountPresenter(private val model: CountModel,
                     private val view: CountView) {

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

    private fun subscribeObservers(activity: Activity) {

        // Subscribe listeners for the operators
        RxBus.subscribe(activity, object : OperatorButtonPressedObserver() {
            override fun onEvent(value: OnButtonPressed) {
                handleOperatorButtonPressed(value.operator, value.value)
            }
        })

        // Subscribe listener for the equal button
        RxBus.subscribe(activity, object : EqualButtonPressedObserver() {
            override fun onEvent(value: OnEqualButtonPressed) {
                makeOperation(value.value.toDouble())
            }
        })
    }

    /**
     * Here it'll be decided the operation to be performed
     *
     * @param value value that comes with the equal button pressed
     */
    fun makeOperation(value: Double) {
        when (model.operation) {
            CountView.PLUS -> model.addition(value) { updateResult(it) }
            CountView.MINUS -> model.subtraction(value) { updateResult(it) }
            CountView.MULTIPLIER -> model.multiplication(value) { updateResult(it) }
            CountView.DIVIDER -> model.division(value) { updateResult(it) }
            CountView.SPACE -> updateResult(value)
        }
    }

    /**
     * In order to hold the first number of the operation, this function
     * is gonna do that and also update the UI
     */
    fun handleOperatorButtonPressed(operator: String, value: String) {
        model.defineOperator(operator)
        model.addNumber(value.toDouble())
        view.cleanInput()
    }

    /**
     * This function is going to handle the operation result and
     * will notify to the view when is done
     *
     * @param result value from EditText
     */
    fun updateResult(result: Double) {
        view.setResult(result.toString())
        view.cleanInput()
        model.defineOperator(CountView.SPACE)
    }

}