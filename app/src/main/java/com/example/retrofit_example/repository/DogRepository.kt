package com.example.retrofit_example.repository

import android.content.Context
import com.example.retrofit_example.model.DogModelResponse
import com.example.retrofit_example.webservice.ApiService
import com.example.retrofit_example.webservice.ApiUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DogRepository(val context: Context) {
    private var apiService: ApiService = ApiUtils.getApiService()

    suspend fun getDogsByBreeds(query: String): DogModelResponse? {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getDogsByBreeds("$query/images/random")
                if (response.isSuccessful) {
                    // Extraemos el cuerpo de la respuesta solo si es exitosa
                    response.body()  // Devuelve el cuerpo directamente (puede ser nulo)
                } else {
                    null  // Devuelve null si la respuesta no fue exitosa
                }
            } catch (e: Exception) {
                e.printStackTrace()
                null  // En caso de error, devolvemos null
            }
        }
    }

}