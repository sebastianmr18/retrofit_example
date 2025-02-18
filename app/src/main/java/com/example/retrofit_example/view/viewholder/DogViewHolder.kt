package com.example.retrofit_example.view.viewholder

import android.media.Image
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit_example.databinding.ItemDogBinding
import com.squareup.picasso.Picasso

class DogViewHolder(view: View):RecyclerView.ViewHolder(view) {

    private val binding = ItemDogBinding.bind(view)

    fun bind(image: String){
        Picasso.get().load(image).into(binding.imageViewDog)
    }
}