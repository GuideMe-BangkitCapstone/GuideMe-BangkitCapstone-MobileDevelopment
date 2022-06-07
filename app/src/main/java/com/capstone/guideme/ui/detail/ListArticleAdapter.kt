package com.capstone.guideme.ui.detail

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.guideme.databinding.ItemRowArticleBinding
import com.capstone.guideme.model.ListArticleItem

class ListArticleAdapter(private val article: ArrayList<ListArticleItem>) :
    RecyclerView.Adapter<ListArticleAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val binding =
            ItemRowArticleBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val article = article[position]
        with(holder.binding){
            Glide.with(holder.itemView.context)
                .load(article.photoUrl)
                .into(imgItemPhoto)
            tvArticleTitle.text = article.title
            tvArticleDescription.text = article.description
            tvArticleSource.text = article.articleUrl
            holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(article) }
        }
    }

    override fun getItemCount(): Int = article.size

    class ListViewHolder(var binding: ItemRowArticleBinding) : RecyclerView.ViewHolder(binding.root)

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: ListArticleItem)
    }
}