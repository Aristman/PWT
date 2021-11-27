package com.example.poketwordtranslator.domain.presenter

import com.example.poketwordtranslator.data.NetworkRepositoryImpl
import com.example.poketwordtranslator.domain.interactor.Interactor
import com.example.poketwordtranslator.domain.interactor.InteractorImpl
import com.example.poketwordtranslator.domain.model.Translations
import com.example.poketwordtranslator.presentation.model.AppViewState
import com.example.poketwordtranslator.presentation.toUi
import com.example.poketwordtranslator.presentation.view.MainView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class MainPresenterImpl(
    private val interactor: Interactor<Translations> = InteractorImpl(NetworkRepositoryImpl())
) : MainPresenter {
    private var attachView: MainView? = null
    private val disposableContainer = CompositeDisposable()

    override fun attachView(view: MainView) {
        if (attachView != view) {
            attachView = view
        }
    }

    override fun detachView(view: MainView) {
        if (view == attachView) {
            attachView = null
        }
    }

    override fun getData(word: String, isOnline: Boolean) {
        disposableContainer.add(interactor.getData(word, fromRemoteSource = true)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                attachView?.handlingData(AppViewState.Loading(null))
            }
            .toList()
            .subscribe(
                {
                    attachView?.handlingData(AppViewState.Success(it.map { item -> item.toUi() }))
                },
                {
                    attachView?.handlingData(AppViewState.Error(it))
                },
            )
        )
    }
}