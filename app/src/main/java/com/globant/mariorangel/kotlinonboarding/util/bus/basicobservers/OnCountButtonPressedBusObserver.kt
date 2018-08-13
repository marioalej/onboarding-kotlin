package com.globant.mariorangel.kotlinonboarding.util.bus.basicobservers

import com.globant.mariorangel.kotlinonboarding.util.bus.BusObserver

abstract class OnCountButtonPressedBusObserver : BusObserver<OnCountButtonPressedBusObserver.OnCountButtonPressed>
(OnCountButtonPressed::class.java) {

    class OnCountButtonPressed
}