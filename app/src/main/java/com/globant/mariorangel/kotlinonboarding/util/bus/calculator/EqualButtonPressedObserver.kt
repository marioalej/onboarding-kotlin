package com.globant.mariorangel.kotlinonboarding.util.bus.calculator

import com.globant.mariorangel.kotlinonboarding.util.bus.BusObserver

abstract class EqualButtonPressedObserver :
        BusObserver<EqualButtonPressedObserver.OnEqualButtonPressed>(OnEqualButtonPressed::class.java) {

    class OnEqualButtonPressed(val value: String)
}