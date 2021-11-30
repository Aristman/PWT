package ru.marslab.pocketwordtranslator.presentation.screens

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.marslab.pocketwordtranslator.domain.interactor.Interactor
import ru.marslab.pocketwordtranslator.domain.model.Translations
import ru.marslab.pocketwordtranslator.presentation.model.AppViewState
import ru.marslab.pocketwordtranslator.presentation.toUi
import ru.marslab.pocketwordtranslator.presentation.viewmodels.TranslationViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val interactor: Interactor<Translations>
) : ViewModel(), TranslationViewModel {

    private val disposableContainer = CompositeDisposable()

    private val _translations = MutableStateFlow<AppViewState>(AppViewState.Init)
    override val translations: StateFlow<AppViewState> =
        _translations.asStateFlow()


    override fun getTranslations(word: String) {
        disposableContainer.add(interactor.getData(word, fromRemoteSource = true)
            .doOnSubscribe {
                _translations.tryEmit(AppViewState.Loading(null))
            }
            .map {
                it.toUi()
            }
            .toList()
            .subscribe(
                {
                    _translations.tryEmit(AppViewState.Success(it))
                },
                {
                    _translations.tryEmit(AppViewState.Error(it))
                }
            )
        )
    }

}