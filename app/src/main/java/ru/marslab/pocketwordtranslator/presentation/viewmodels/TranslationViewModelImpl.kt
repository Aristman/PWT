package ru.marslab.pocketwordtranslator.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.marslab.pocketwordtranslator.domain.interactor.Interactor
import ru.marslab.pocketwordtranslator.domain.model.Translations
import ru.marslab.pocketwordtranslator.presentation.model.AppAction
import ru.marslab.pocketwordtranslator.presentation.model.AppViewState
import ru.marslab.pocketwordtranslator.presentation.model.TranslateWordUi
import ru.marslab.pocketwordtranslator.presentation.toUi
import javax.inject.Inject

@HiltViewModel
class TranslationViewModelImpl @Inject constructor(
    private val interactor: Interactor<Translations>
) : ViewModel(), TranslationViewModel {

    private val disposableContainer = CompositeDisposable()

    private val _translationsState = MutableStateFlow<AppViewState>(AppViewState.Init)
    override val translationsState: StateFlow<AppViewState> =
        _translationsState.asStateFlow()

    private val _searchWordDialogAction = MutableSharedFlow<AppAction>()
    override val searchWordDialogAction: SharedFlow<AppAction> =
        _searchWordDialogAction.asSharedFlow()

    private val _translationList = MutableStateFlow<List<TranslateWordUi>>(emptyList())

    override fun getTranslations(word: String) {
        disposableContainer.add(interactor.getData(word, fromRemoteSource = true)
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

    override fun showSearchDialog() {
        viewModelScope.launch {
            _searchWordDialogAction.emit(AppAction.Show)
        }
    }

}