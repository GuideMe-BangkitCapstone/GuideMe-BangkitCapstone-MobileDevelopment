package com.capstone.guideme.ui.detail

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.capstone.guideme.databinding.ActivityDetailBinding
import com.capstone.guideme.model.DetailPlacesResponse
import com.capstone.guideme.model.ListArticleItem
import com.capstone.guideme.model.ListPhotoItem
import com.capstone.guideme.utils.UserPreference
import com.capstone.guideme.utils.ViewModelFactory
import com.capstone.guideme.utils.showLoading


class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailViewModel: DetailViewModel
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        detailViewModel = ViewModelProvider(
            this,
            ViewModelFactory(UserPreference.getInstance(dataStore))
        )[DetailViewModel::class.java]

        detailViewModel.getUser().observe(this){ user ->
            val name = intent.getStringExtra(EXTRA_NAME)!!

            detailViewModel.getDetailPlace(name, user.token)
            detailViewModel.isLoading.observe(this){
                showLoading(it, binding.viewLoading)
            }

            detailViewModel.detailPlace.observe(this){ detail ->
                setData(detail)
                detailViewModel.getDetailAlbum(detail.placeId, user.token)
                detailViewModel.getDetailArticle(detail.placeId, user.token)
            }

            detailViewModel.detailAlbums.observe(this){
                setPhoto(it)
            }

            detailViewModel.detailArticle.observe(this){
                setArticle(it)
            }
        }

    }

    private fun setData(place: DetailPlacesResponse) {
        with(binding) {
            tvDetailPlaceName.text = place.name
            tvAddress.text = place.address
            tvDescription.text = place.description
        }
    }

    private fun setArticle(articles: List<ListArticleItem>) {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        val listArticle = ArrayList<ListArticleItem>()

        for (place in articles) {
            listArticle.clear()
            listArticle.addAll(articles)
        }
        val adapter = ListArticleAdapter(listArticle)
        binding.recyclerView.adapter = adapter
        adapter.setOnItemClickCallback(object : ListArticleAdapter.OnItemClickCallback {
            override fun onItemClicked(data: ListArticleItem) {
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(data.articleUrl)
                startActivity(i)
            }
        })
    }

    private fun setPhoto(photos: List<ListPhotoItem>) {
        with(binding){
            Glide.with(this@DetailActivity)
                .load(photos[0].photoUrl)
                .into(imageView1)
            Glide.with(this@DetailActivity)
                .load(photos[1].photoUrl)
                .into(imageView2)
            Glide.with(this@DetailActivity)
                .load(photos[2].photoUrl)
                .into(imageView3)
        }
    }

    companion object {
        const val EXTRA_NAME = "placeName"
    }

}