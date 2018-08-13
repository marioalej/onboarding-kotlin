package com.globant.mariorangel.kotlinonboarding.util.bus.basicobservers

import com.globant.mariorangel.kotlinonboarding.util.bus.BusObserver

abstract class OnResetButtonPressedBusObserver : BusObserver<OnResetButtonPressedBusObserver.OnResetButtonPressed>
(OnResetButtonPressed::class.java) {

    class OnResetButtonPressed
}