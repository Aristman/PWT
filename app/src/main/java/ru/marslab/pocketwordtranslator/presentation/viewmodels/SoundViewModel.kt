package ru.marslab.pocketwordtranslator.presentation.viewmodels

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.marslab.pocketwordtranslator.presentation.model.AppViewState
import ru.marslab.shared.domain.interactor.SoundInteractor

private const val LOAD_SOUND_ERROR = "Не получен файл озвучки"

class SoundViewModel(
    private val soundInteractor: SoundInteractor
) : ViewModel() {

    private val _soundState = MutableStateFlow<AppViewState<Uri, Throwable>>(AppViewState.Init)
    val soundState: StateFlow<AppViewState<Uri, Throwable>> =
        _soundState.asStateFlow()

    fun getWordSound(url: String, word: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _soundState.tryEmit(AppViewState.Loading(null))
                val wordSound = soundInteractor.getWordSound(url, word)
                if (wordSound != null) {
                    _soundState.tryEmit(AppViewState.Success(wordSound))
                } else {
                    _soundState.tryEmit(AppViewState.Error(Throwable(LOAD_SOUND_ERROR)))
                }
            } catch (e: Exception) {
                _soundState.tryEmit(AppViewState.Error(e))
            }
        }
    }
}
