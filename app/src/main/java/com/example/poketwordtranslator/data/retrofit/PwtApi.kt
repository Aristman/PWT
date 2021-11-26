package com.example.poketwordtranslator.data.retrofit

import com.example.poketwordtranslator.data.model.WordNW
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface PwtApi {
    @GET("words/search")
    fun searchWord(@Query("search") word: String): Single<List<WordNW>>
}