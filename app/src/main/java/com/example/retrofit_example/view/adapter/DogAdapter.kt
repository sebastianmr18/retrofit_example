package com.example.retrofit_example.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit_example.R
import com.example.retrofit_example.view.viewholder.DogViewHolder

class DogAdapter(val images: List<String>): RecyclerView.Adapter<DogViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return DogViewHolder(layoutInflater.inflate(R.layout.item_dog, parent, false))
    }

    override fun getItemCount(): Int = images.size

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        val item: String = images[position]
        holder.bind(item)
    }

}