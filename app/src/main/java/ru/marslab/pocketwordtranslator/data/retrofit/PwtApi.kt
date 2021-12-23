package ru.marslab.pocketwordtranslator.data.retrofit

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.marslab.pocketwordtranslator.data.model.WordNW

interface PwtApi {
    @GET("words/search")
    fun searchWord(@Query("search") word: String): Single<List<WordNW>>
}
