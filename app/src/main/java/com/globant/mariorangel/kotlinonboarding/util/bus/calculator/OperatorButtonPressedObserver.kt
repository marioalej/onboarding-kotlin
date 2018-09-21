package com.globant.mariorangel.kotlinonboarding.util.bus.calculator

import com.globant.mariorangel.kotlinonboarding.util.bus.BusObserver

abstract class OperatorButtonPressedObserver :
        BusObserver<OperatorButtonPressedObserver.OnButtonPressed>(OnButtonPressed::class.java) {

    class OnButtonPressed(val value: String, val operator: String)
}