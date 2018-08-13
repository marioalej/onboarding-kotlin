package com.globant.mariorangel.kotlinonboarding.mvp.presenter

import com.globant.mariorangel.kotlinonboarding.mvp.model.CountModel
import com.globant.mariorangel.kotlinonboarding.mvp.view.CountView
import com.globant.mariorangel.kotlinonboarding.util.bus.RxBus
import com.globant.mariorangel.kotlinonboarding.util.bus.calculator.EditTextChangedObserver

class CountPresenter(private val model: CountModel,
                     private val view: CountView) {

    //Bind observers to Activity
    fun register() {

        val activity = view.activity

        if (activity != null) {

            RxBus.subscribe(activity, object : EditTextChangedObserver(){
                override fun onEvent(value: OnTextChanged) {
                    handleEditTextPressed(value.s)
                }
            })
        }
    }

    //Unbind observers to Activity
    fun unRegister() {
        val activity = view.activity ?: return

        RxBus.clear(activity)
    }

    /**
     * This method is going to handle the operation result and
     * will notify to the view when is done
     *
     * @param value value from TextEdit
     */
    fun handleEditTextPressed(value: String) {
        model.plus(value) {
            if(it > 0)
                view.setResult(it.toString())
            else if(it == -1)
                view.fireToastWithIncorrectFormat()
        }
    }
}