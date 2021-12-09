package ru.marslab.pocketwordtranslator.domain.interactor

import android.net.Uri
import ru.marslab.pocketwordtranslator.domain.repository.FileRepository
import ru.marslab.pocketwordtranslator.domain.repository.NetworkRepository

class SoundInteractorImpl(
    private val networkRepository: NetworkRepository,
    private val fileRepository: FileRepository
) : SoundInteractor {

    @Throws(Exception::class)
    override suspend fun getWordSound(url: String, filename: String): Uri? {
        val source = networkRepository.getWordSound(url)
        return source?.let { fileRepository.saveFileToCache(filename, it) }
    }
}

