package com.example.getar.data.repository

import com.example.getar.data.model.Gempa
import com.example.getar.data.remote.BmkgApiService

class GempaRepository(private val api: BmkgApiService) {
    suspend fun getGempa(): List<Gempa> {
        return api.getGempaTerkini().infogempa?.gempa ?: emptyList()
    }
}
