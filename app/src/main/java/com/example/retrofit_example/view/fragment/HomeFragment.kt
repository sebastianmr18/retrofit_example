package com.example.retrofit_example.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.util.query
import androidx.viewbinding.ViewBinding
import com.example.retrofit_example.R
import com.example.retrofit_example.databinding.FragmentHomeBinding
import com.example.retrofit_example.model.DogModelResponse
import com.example.retrofit_example.repository.DogRepository
import com.example.retrofit_example.view.adapter.DogAdapter
import com.example.retrofit_example.viewmodel.DogViewModel
import com.example.retrofit_example.webservice.ApiService
import com.example.retrofit_example.webservice.RetrofitClient.getRetrofit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeFragment : Fragment(), SearchView.OnQueryTextListener {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: DogAdapter
    private val dogViewModel: DogViewModel by viewModels()
    private val dogImages = mutableListOf<String>() // Inicializa tu lista

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchViewDogs.setOnQueryTextListener(this)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        adapter = DogAdapter(dogImages)
        binding.recyclerViewDogs.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewDogs.adapter = adapter
    }

    private fun observerDogs(query: String) {
        dogViewModel.getDog(query)
        dogViewModel.dogResponse.observe(viewLifecycleOwner, Observer { puppy ->
            lifecycleScope.launch {
                if (puppy != null) {
                    val image = puppy.images // Obtén la imagen
                    Log.d("API_CALL", "Respuesta exitosa. Imagen obtenida: $image")

                    dogImages.clear() // Limpia la lista de imágenes
                    if (image.isNotEmpty()) {
                        dogImages.add(image) // Agrega la imagen a la lista si no está vacía
                    }
                    adapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(context, "Algo falló", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }



    override fun onQueryTextSubmit(query: String): Boolean {
        if(!query.isNullOrEmpty()) {
            observerDogs(query.lowercase())
        }
        return true
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        return true
    }
}