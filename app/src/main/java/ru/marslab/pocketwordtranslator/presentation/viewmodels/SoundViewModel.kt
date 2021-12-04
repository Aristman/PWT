package ru.marslab.pocketwordtranslator.presentation.viewmodels

import android.net.Uri
import kotlinx.coroutines.flow.StateFlow
import ru.marslab.pocketwordtranslator.presentation.model.AppViewState

interface SoundViewModel {
    val soundState: StateFlow<AppViewState<Uri, Throwable>>
    fun getWordSound(url: String, word: String)
}