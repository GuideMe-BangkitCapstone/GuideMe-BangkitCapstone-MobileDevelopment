package com.capstone.guideme.ui.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.guideme.databinding.ItemRowHistoryBinding
import com.capstone.guideme.model.ListHistoryItem
import kotlin.collections.ArrayList

class ListHistoryAdapter(private val histories: ArrayList<ListHistoryItem>) :
    RecyclerView.Adapter<ListHistoryAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val binding =
            ItemRowHistoryBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val history = histories[position]

        Glide.with(holder.itemView.context)
            .load(history.photoUrl)
            .into(holder.binding.imgItemPhoto)
        holder.binding.tvNamePlace.text = history.name
        holder.binding.tvDate.text= history.createdAt
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(history) }
    }

    override fun getItemCount(): Int = histories.size

    class ListViewHolder(var binding: ItemRowHistoryBinding) : RecyclerView.ViewHolder(binding.root)

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: ListHistoryItem)
    }
}