package com.globant.mariorangel.kotlinonboarding

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.globant.mariorangel.kotlinonboarding.mvp.model.CountModel
import com.globant.mariorangel.kotlinonboarding.mvp.presenter.CountPresenter
import com.globant.mariorangel.kotlinonboarding.mvp.view.CountView
import com.globant.mariorangel.kotlinonboarding.util.bus.RxBus

class MainActivity : AppCompatActivity() {

    private var presenter: CountPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = CountPresenter(CountModel(), CountView(this))
    }

    override fun onDestroy() {
        super.onDestroy()
        RxBus.clear(this)
    }

}