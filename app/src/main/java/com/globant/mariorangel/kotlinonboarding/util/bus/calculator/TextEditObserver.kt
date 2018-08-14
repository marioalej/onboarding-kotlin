package com.globant.mariorangel.kotlinonboarding.util.bus.calculator

import com.globant.mariorangel.kotlinonboarding.util.bus.BusObserver

abstract class TextEditObserver :
        BusObserver<TextEditObserver.OnKeyPressed>(OnKeyPressed::class.java) {

    class OnKeyPressed(val s: String, val operator: String)

}