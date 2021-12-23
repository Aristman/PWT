package ru.marslab.shared.domain.interactor

import android.net.Uri

interface SoundInteractor {
    suspend fun getWordSound(url: String, filename: String): Uri?
}
