package com.example.getar.data.remote

import com.example.getar.data.model.GempaResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface BmkgApiService {
    @GET("DataMKG/TEWS/gempaterkini.json")
    suspend fun getGempaTerkini(): GempaResponse
}

object RetrofitClient {
    private const val BASE_URL = "https://data.bmkg.go.id/"

    val api: BmkgApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BmkgApiService::class.java)
    }
}
