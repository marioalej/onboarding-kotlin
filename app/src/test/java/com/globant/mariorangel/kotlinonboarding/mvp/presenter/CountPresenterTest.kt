package com.globant.mariorangel.kotlinonboarding.mvp.presenter

import com.globant.mariorangel.kotlinonboarding.mvp.model.CountModel
import com.globant.mariorangel.kotlinonboarding.mvp.view.CountView
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


class CountPresenterTest {

    lateinit var presenter : CountPresenter

    private val model = Mockito.mock(CountModel::class.java)!!
    private val view = Mockito.mock(CountView::class.java)!!


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = CountPresenter(model, view)
    }

    @Test
    fun shouldMakeAnAddition() {

        Mockito.`when`(model.number).thenReturn(ANY_DOUBLE)
        Mockito.`when`(model.operation).thenReturn(PLUS)

        presenter.makeOperation(ANY_DOUBLE)

        presenter.updateResult(Mockito.anyDouble())

        Mockito.verify(view).setResult(Mockito.anyString())
        Mockito.verify(view).cleanInput()
        Mockito.verifyNoMoreInteractions(view)
    }

    companion object {
        private const val ANY_DOUBLE = 0.0
        private const val PLUS = "+"
    }
}