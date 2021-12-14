package ru.marslab.pocketwordtranslator.presentation.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.flow.*
import ru.marslab.pocketwordtranslator.domain.interactor.TranslationInteractor
import ru.marslab.pocketwordtranslator.presentation.model.AppViewState
import ru.marslab.pocketwordtranslator.presentation.model.TranslateWordUi
import ru.marslab.pocketwordtranslator.presentation.toUi
import javax.inject.Inject

@HiltViewModel
class TranslationViewModelImpl @Inject constructor(
    private val translationInteractor: TranslationInteractor
) : ViewModel(), TranslationViewModel {

    private val disposableContainer = CompositeDisposable()

    private val _translationsState =
        MutableStateFlow<AppViewState<List<TranslateWordUi>, Throwable>>(AppViewState.Init)
    override val translationsState = _translationsState.asStateFlow()

    override fun getTranslations(word: String) {
        disposableContainer.addAll(
            translationInteractor.getData(word, fromRemoteSource = true)
                .doOnSubscribe {
                    _translationsState.tryEmit(AppViewState.Loading(null))
                }
                .map {
                    it.toUi()
                }
                .toList()
                .subscribe(
                    {
                        _translationsState.tryEmit(AppViewState.Success(it))
                    },
                    {
                        _translationsState.tryEmit(AppViewState.Error(it))
                    }
                )
        )
    }

    override fun onCleared() {
        disposableContainer.dispose()
        super.onCleared()
    }
}