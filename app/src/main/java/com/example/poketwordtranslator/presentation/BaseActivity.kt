package com.example.poketwordtranslator.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.poketwordtranslator.domain.presenter.Presenter
import com.example.poketwordtranslator.presentation.model.ViewState

abstract class BaseActivity<T : ViewState> : AppCompatActivity(), View {

    private lateinit var presenter: Presenter<T, View>

    protected abstract fun createPresenter(): Presenter<T, View>

    abstract override fun renderData(viewState: ViewState)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createPresenter()
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView(this)
    }
}
