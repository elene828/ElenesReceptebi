package com.example.kulinaria.models

import com.google.gson.annotations.SerializedName

data class recepti(
    @SerializedName("saxeli") val saxeli: String?,
    @SerializedName("info") val info: String?
)