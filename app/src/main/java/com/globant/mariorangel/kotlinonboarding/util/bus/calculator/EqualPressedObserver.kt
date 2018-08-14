package com.globant.mariorangel.kotlinonboarding.util.bus.calculator

import com.globant.mariorangel.kotlinonboarding.util.bus.BusObserver

abstract class EqualPressedObserver :
        BusObserver<EqualPressedObserver.OnEqualPressed>(OnEqualPressed::class.java) {

    class OnEqualPressed(val s: String)
}