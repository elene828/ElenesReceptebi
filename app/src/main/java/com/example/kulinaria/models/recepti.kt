package com.example.kulinaria.models

import com.google.gson.annotations.SerializedName

data class Recepti(

@SerializedName("saxeli") val saxeli: String?,
@SerializedName("info") val info: String?,
@SerializedName("imageUrl") val imageUrl: String?


)