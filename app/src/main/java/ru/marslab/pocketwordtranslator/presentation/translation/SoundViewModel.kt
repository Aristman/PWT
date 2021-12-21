package ru.marslab.pocketwordtranslator.presentation.translation

import android.net.Uri
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.marslab.pocketwordtranslator.presentation.util.BaseComposeViewModel
import ru.marslab.shared.domain.interactor.SoundInteractor

private const val LOAD_SOUND_ERROR = "Не получен файл озвучки"

class SoundViewModel(
    private val soundInteractor: SoundInteractor
) : BaseComposeViewModel<Uri, Throwable>() {

    fun getWordSound(url: String, word: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                setLoading()
                val wordSound = soundInteractor.getWordSound(url, word)
                if (wordSound != null) {
                    setSuccessful(wordSound)
                } else {
                    setError(Throwable(LOAD_SOUND_ERROR))
                }
            } catch (e: Exception) {
                setError(e)
            }
        }
    }
}
