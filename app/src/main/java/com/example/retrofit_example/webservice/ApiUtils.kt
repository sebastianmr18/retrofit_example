package com.example.retrofit_example.webservice

class ApiUtils {
    companion object {
        fun getApiService(): ApiService{
            return RetrofitClient.getRetrofit().create(ApiService::class.java)
        }
    }
}