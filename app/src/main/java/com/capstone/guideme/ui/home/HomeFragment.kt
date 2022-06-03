package com.capstone.guideme.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.guideme.databinding.FragmentHomeBinding
import com.capstone.guideme.model.ListPlacesItem
import com.capstone.guideme.ui.detail.DetailActivity
import com.capstone.guideme.utils.showLoading
import java.util.ArrayList

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "GuideMe"

        homeViewModel.findPlaces()
        homeViewModel.isLoading.observe(viewLifecycleOwner){
            showLoading(it, binding.viewLoading)
        }
        homeViewModel.listPlaces.observe(viewLifecycleOwner) {
            setPlaces(it)
        }
    }

    private fun setPlaces(places: List<ListPlacesItem>) {
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)

        val listPlace = ArrayList<ListPlacesItem>()
        for (place in places) {
            listPlace.clear()
            listPlace.addAll(places)
        }
        val adapter = ListPlacesAdapter(listPlace)
        binding.recyclerView.adapter = adapter
        adapter.setOnItemClickCallback(object : ListPlacesAdapter.OnItemClickCallback {
            override fun onItemClicked(data: ListPlacesItem) {
                showPlace(data)
            }
        })
    }

    private fun showPlace(data: ListPlacesItem) {
        activity?.let{
            val intent = Intent (it, DetailActivity::class.java).apply {
                putExtra(DetailActivity.EXTRA_NAME, data.name)
            }
            it.startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}