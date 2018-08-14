package com.globant.mariorangel.kotlinonboarding.mvp.presenter

import android.app.Activity
import com.globant.mariorangel.kotlinonboarding.mvp.model.CountModel
import com.globant.mariorangel.kotlinonboarding.mvp.view.CountView
import com.globant.mariorangel.kotlinonboarding.util.bus.RxBus
import com.globant.mariorangel.kotlinonboarding.util.bus.calculator.*

class CountPresenter(private val model: CountModel,
                     private val view: CountView) {

    //It will consider if the number is the first number input by the user
    private var firstNumber = true

    //It's whom determine the operation inside the presenter
    private var operation = CountView.BLANK_SPACE

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
        RxBus.subscribe(activity, object : TextEditObserver() {
            override fun onEvent(value: OnKeyPressed) {
                operation = value.operator
                model.addFirstNumber(value.s) { handleFirstNumberCallback(it) }
            }
        })
        RxBus.subscribe(activity, object : EqualPressedObserver() {
            override fun onEvent(value: OnEqualPressed) {
                if(!firstNumber)
                    makeOperation(value.s)
            }

        })
    }

    private fun makeOperation(value: String) {
        when (operation) {
            CountView.PLUS -> {
                model.addition(value) {
                    updateResult(it)
                }
            }
            CountView.MINUS -> {
                model.subtraction(value) {
                    updateResult(it)
                }
            }
            CountView.MULTIPLIER -> {
                model.multiplication(value) {
                    updateResult(it)
                }
            }
            CountView.DIVIDER -> {
                model.division(value) {
                    updateResult(it)
                }
            }
        }
    }

    /**
     * This function is going to handle the operation result and
     * will notify to the view when is done
     *
     * @param result value from EditText
     */
    private fun updateResult(result: Double?) {
        if (result == null) {
            view.fireShortToast(WRONG_FORMAT)
        } else {
            view.setResult(result.toString())
            view.cleanNumberLabel()
            view.cleanInput()
            firstNumber = true
        }
    }

    /**
     * This function is going to handle first user input
     *
     * @param value     value from EditText
     */
    private fun handleFirstNumberCallback(value: Double?) {
        if (value == null) {
            view.fireShortToast(WRONG_FORMAT)
        } else {
            firstNumber = false
            view.updateLabel(value.toString())
        }
    }

    companion object {
        private const val WRONG_FORMAT = "Incorrect number format"
    }
}