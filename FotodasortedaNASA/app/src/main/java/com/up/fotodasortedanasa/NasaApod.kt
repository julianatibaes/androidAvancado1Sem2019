package com.up.fotodasortedanasa

import com.google.gson.annotations.SerializedName

data class NasaApod (
    @SerializedName("explanation") var explicacao: String = "",
    @SerializedName("media_type") var tipo_midia: String = "",
    @SerializedName("title") var titulo: String = "",
    @SerializedName("url") var url: String = ""
)