package com.globant.mariorangel.kotlinonboarding.mvp.model

class CountModel {

    var count = 0
        private set

    fun reset() {
        count = 0
    }

    fun inc() {
        count += 1
    }

}