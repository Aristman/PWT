package ru.marslab.pocketwordtranslator.presentation.viewmodels

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.marslab.pocketwordtranslator.domain.interactor.SoundInteractor
import ru.marslab.pocketwordtranslator.presentation.model.AppViewState
import javax.inject.Inject

@HiltViewModel
class SoundViewModelImpl @Inject constructor(
    private val soundInteractor: SoundInteractor
) : ViewModel(), SoundViewModel {

    private val _soundState = MutableStateFlow<AppViewState<Uri, Throwable>>(AppViewState.Init)
    override val soundState: StateFlow<AppViewState<Uri, Throwable>> =
        _soundState.asStateFlow()

    override fun getWordSound(url: String, word: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _soundState.tryEmit(AppViewState.Loading(null))
                val wordSound = soundInteractor.getWordSound(url, word)
                if (wordSound != null) {
                    _soundState.tryEmit(AppViewState.Success(wordSound))
                } else {
                    _soundState.tryEmit(AppViewState.Error(Throwable("Не получен файл озвучки")))
                }
            } catch (e: Exception) {
                _soundState.tryEmit(AppViewState.Error(e))
            }
        }
    }
}