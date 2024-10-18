package com.example.retrofit_example.model

import com.google.gson.annotations.SerializedName

data class DogModelResponse(
    @SerializedName("status") var status:String,
    @SerializedName("message") var images: String
    )