package com.globant.mariorangel.kotlinonboarding.mvp.presenter

import com.globant.mariorangel.kotlinonboarding.mvp.model.CountModel
import com.globant.mariorangel.kotlinonboarding.mvp.view.CountView
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class CountPresenterTest {

    private lateinit var presenter : CountPresenter

    private val model = Mockito.mock(CountModel::class.java)!!
    private val view = Mockito.mock(CountView::class.java)!!

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = CountPresenter(model, view)
    }

    @Test
    fun shouldPlusFirstAndSecondNumber() {
        //Mockito.`when`(model.addition("10", ))
    }


}