package com.globant.mariorangel.kotlinonboarding.mvp.presenter

import com.globant.mariorangel.kotlinonboarding.MainActivity
import com.globant.mariorangel.kotlinonboarding.mvp.model.CountModel
import com.globant.mariorangel.kotlinonboarding.mvp.view.CountView
import com.globant.mariorangel.kotlinonboarding.util.bus.basicobservers.OnCountButtonPressedBusObserver
import com.globant.mariorangel.kotlinonboarding.util.bus.basicobservers.OnResetButtonPressedBusObserver
import com.globant.mariorangel.kotlinonboarding.util.bus.RxBus
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class CountPresenterTest {

    private lateinit var presenter : CountPresenter

    private val model = Mockito.mock(CountModel::class.java)!!
    private val view = Mockito.mock(CountView::class.java)!!

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = CountPresenter(model!!, view)
    }

    @Test
    fun shouldPlusFirstAndSecondNumber() {
        //Mockito.`when`(model.plus("10", ))
    }


}