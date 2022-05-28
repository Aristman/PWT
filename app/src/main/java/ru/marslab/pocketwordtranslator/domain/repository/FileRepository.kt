package ru.marslab.pocketwordtranslator.domain.repository

import android.net.Uri
import okio.BufferedSource

interface FileRepository {
    fun saveFileToCache(filename: String, stream: BufferedSource): Uri
}
