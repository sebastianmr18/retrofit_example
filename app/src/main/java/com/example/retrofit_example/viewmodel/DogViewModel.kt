package com.example.retrofit_example.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.retrofit_example.model.DogModelResponse
import kotlinx.coroutines.launch
import com.example.retrofit_example.repository.DogRepository

class DogViewModel(application: Application) : AndroidViewModel(application) {
    val context = getApplication<Application>()
    private val dogRepository = DogRepository(context)

    // Para almacenar un único perro por raza
    private val _dogResponse = MutableLiveData<DogModelResponse?>()
    val dogResponse: LiveData<DogModelResponse?> get() = _dogResponse

    private val _progressState = MutableLiveData(false)
    val progressState: LiveData<Boolean> = _progressState

    fun getDog(query: String) {
        viewModelScope.launch {
            _progressState.value = true
            try {
                _dogResponse.value = dogRepository.getDogsByBreeds(query) // Cambiado aquí
                _progressState.value = false
            } catch (e: Exception) {
                _progressState.value = false
                _dogResponse.value = null // Manejo de error, null
            }
        }
    }
}
