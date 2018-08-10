package com.globant.mariorangel.kotlinonboarding.mvp.presenter

import com.globant.mariorangel.kotlinonboarding.MainActivity
import com.globant.mariorangel.kotlinonboarding.mvp.model.CountModel
import com.globant.mariorangel.kotlinonboarding.mvp.view.CountView
import com.globant.mariorangel.kotlinonboarding.util.bus.OnCountButtonPressedBusObserver
import com.globant.mariorangel.kotlinonboarding.util.bus.OnResetButtonPressedBusObserver
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
    private var presenter: CountPresenter? = null
    private var model: CountModel? = null
    @Mock
    lateinit var view: CountView
    @Mock
    lateinit var activity: MainActivity

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        model = CountModel()
        // When
        Mockito.`when`(view.activity).thenReturn(activity)

        presenter = CountPresenter(model!!, view)
    }

    @Test
    fun isShouldIncCountByOne() {
        model!!.reset()
        RxBus.post(OnCountButtonPressedBusObserver.OnCountButtonPressed())

        val count = "1"
        assertEquals(model!!.count, 1)
        verify(view).setCount(count)
    }

    @Test
    fun isShouldResetCount() {
        RxBus.post(OnCountButtonPressedBusObserver.OnCountButtonPressed())
        RxBus.post(OnCountButtonPressedBusObserver.OnCountButtonPressed())
        RxBus.post(OnCountButtonPressedBusObserver.OnCountButtonPressed())
        var count = 3
        assertEquals(model!!.count, count)

        RxBus.post(OnResetButtonPressedBusObserver.OnResetButtonPressed())
        count = 0
        assertEquals(model!!.count, count)
        val invocations = 4
        verify(view, times(invocations)).setCount(anyString())
    }
}