package ru.marslab.pocketwordtranslator.domain.presenter

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import ru.marslab.pocketwordtranslator.data.NetworkRepositoryImpl
import ru.marslab.pocketwordtranslator.domain.interactor.Interactor
import ru.marslab.pocketwordtranslator.domain.interactor.InteractorImpl
import ru.marslab.pocketwordtranslator.domain.model.Translations
import ru.marslab.pocketwordtranslator.presentation.model.AppViewState
import ru.marslab.pocketwordtranslator.presentation.toUi
import ru.marslab.pocketwordtranslator.presentation.view.MainView

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
            .map {
                it.toUi()
            }
            .toList()
            .subscribe(
                {
                    attachView?.handlingData(AppViewState.Success(it))
                },
                {
                    attachView?.handlingData(AppViewState.Error(it))
                },
            )
        )
    }
}