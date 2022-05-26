package com.capstone.guideme.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.guideme.databinding.ItemRowPlaceBinding
import com.capstone.guideme.model.ListPlacesItem

class ListPlacesAdapter(private val places: ArrayList<ListPlacesItem>) :
    RecyclerView.Adapter<ListPlacesAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val binding =
            ItemRowPlaceBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val place = places[position]

        Glide.with(holder.itemView.context)
            .load(place.photoUrl)
            .into(holder.binding.imgItemPhoto)
        holder.binding.tvNamePlace.text = place.name
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(place) }
    }

    override fun getItemCount(): Int = places.size

    class ListViewHolder(var binding: ItemRowPlaceBinding) : RecyclerView.ViewHolder(binding.root)

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: ListPlacesItem)
    }
}