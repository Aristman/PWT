package ru.marslab.pocketwordtranslator.presentation.viewmodels

import android.net.Uri
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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

    }
}