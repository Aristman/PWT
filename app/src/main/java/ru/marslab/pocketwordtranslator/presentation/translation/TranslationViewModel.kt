package ru.marslab.pocketwordtranslator.presentation.translation

import androidx.lifecycle.viewModelScope
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.marslab.pocketwordtranslator.presentation.toDomainHistory
import ru.marslab.pocketwordtranslator.presentation.toUiState
import ru.marslab.pocketwordtranslator.presentation.util.BaseComposeViewModel
import ru.marslab.shared.domain.interactor.TranslationInteractor

class TranslationViewModel(
    private val translationInteractor: TranslationInteractor
) : BaseComposeViewModel<List<TranslationUiState>, Throwable>() {

    private val disposableContainer = CompositeDisposable()

    fun getTranslations(word: String) {
        disposableContainer.add(
            translationInteractor.getData(word, fromRemoteSource = true)
                .doOnSubscribe {
                    setLoading()
                }
                .map {
                    it.toUiState()
                }
                .toList()
                .subscribe(
                    {
                        setSuccessful(it)
                    },
                    {
                        setError(it)
                    }
                )
        )
    }

    fun saveToHistory(word: TranslationUiState) {
        viewModelScope.launch(Dispatchers.IO) {
            translationInteractor.saveToHistory(word.toDomainHistory())
        }
    }

    override fun onCleared() {
        disposableContainer.dispose()
        super.onCleared()
    }
}
