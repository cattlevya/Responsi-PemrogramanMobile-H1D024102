package com.example.getar.data.model

import com.google.gson.annotations.SerializedName

data class Gempa(
    @SerializedName("Tanggal") val tanggal: String? = null,
    @SerializedName("Jam") val jam: String? = null,
    @SerializedName("DateTime") val dateTime: String? = null,
    @SerializedName("Coordinates") val coordinates: String? = null,
    @SerializedName("Lintang") val lintang: String? = null,
    @SerializedName("Bujur") val bujur: String? = null,
    @SerializedName("Magnitude") val magnitude: String? = null,
    @SerializedName("Kedalaman") val kedalaman: String? = null,
    @SerializedName("Wilayah") val wilayah: String? = null,
    @SerializedName("Potensi") val potensi: String? = null
)

data class Infogempa(
    @SerializedName("gempa") val gempa: List<Gempa>? = null
)

data class GempaResponse(
    @SerializedName("Infogempa") val infogempa: Infogempa? = null
)
