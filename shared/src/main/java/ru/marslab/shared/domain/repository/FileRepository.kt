package ru.marslab.shared.domain.repository

import android.net.Uri
import okio.BufferedSource

interface FileRepository {
    fun saveFileToCache(filename: String, stream: BufferedSource): Uri
}
