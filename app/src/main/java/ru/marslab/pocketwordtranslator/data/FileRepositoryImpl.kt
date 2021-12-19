package ru.marslab.pocketwordtranslator.data

import android.content.Context
import android.net.Uri
import okio.BufferedSource
import okio.buffer
import okio.sink
import ru.marslab.pocketwordtranslator.domain.repository.FileRepository
import java.io.File

class FileRepositoryImpl(private val context: Context) : FileRepository {
    override fun saveFileToCache(filename: String, stream: BufferedSource): Uri {
        val findCacheFile = context.cacheDir.listFiles()?.find { it.name == filename }
        findCacheFile?.let { return Uri.fromFile(it) }
        val file = File(context.cacheDir, filename)
        file
            .sink()
            .buffer()
            .apply {
                writeAll(stream)
                close()
            }
        return Uri.fromFile(file)
    }
}
