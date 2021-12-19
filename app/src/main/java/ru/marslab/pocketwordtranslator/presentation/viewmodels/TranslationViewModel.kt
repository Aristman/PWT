package ru.marslab.pocketwordtranslator.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.marslab.pocketwordtranslator.domain.interactor.TranslationInteractor
import ru.marslab.pocketwordtranslator.presentation.model.AppViewState
import ru.marslab.pocketwordtranslator.presentation.model.TranslateWordUi
import ru.marslab.pocketwordtranslator.presentation.toDomainHistory
import ru.marslab.pocketwordtranslator.presentation.toUi

class TranslationViewModel(
    private val translationInteractor: TranslationInteractor
) : ViewModel() {

    private val disposableContainer = CompositeDisposable()

    private val _translationsState =
        MutableStateFlow<AppViewState<List<TranslateWordUi>, Throwable>>(AppViewState.Init)
    val translationsState = _translationsState.asStateFlow()

    fun getTranslations(word: String) {
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

    fun saveToHistory(word: TranslateWordUi) {
        viewModelScope.launch(Dispatchers.IO) {
            translationInteractor.saveToHistory(word.toDomainHistory())
        }
    }

    override fun onCleared() {
        disposableContainer.dispose()
        super.onCleared()
    }
}
