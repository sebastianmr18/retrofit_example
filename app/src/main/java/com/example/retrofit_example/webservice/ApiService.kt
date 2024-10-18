package com.example.retrofit_example.webservice

import com.example.retrofit_example.model.DogModelResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    @GET
    suspend fun getDogsByBreeds(@Url url: String): Response<DogModelResponse>
}