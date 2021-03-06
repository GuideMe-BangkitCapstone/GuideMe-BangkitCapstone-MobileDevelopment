package com.capstone.guideme.ui.profile

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.guideme.MainActivity
import com.capstone.guideme.databinding.FragmentProfileBinding
import com.capstone.guideme.model.ListHistoryItem
import com.capstone.guideme.model.User
import com.capstone.guideme.ui.detail.DetailActivity
import com.capstone.guideme.utils.showLoading


class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val profileViewModel by viewModels<ProfileViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "GuideMe"

        val activity: MainActivity? = activity as MainActivity?
        val userInfo: User = activity!!.getMyData()

        profileViewModel.getUserDetail(userInfo.token)
        profileViewModel.getUserVisitHistory(userInfo.token)

        profileViewModel.isLoading.observe(viewLifecycleOwner){
            showLoading(it, binding.viewLoading)
        }
        profileViewModel.user.observe(viewLifecycleOwner) {
            setData(it)
        }
        profileViewModel.userHistory.observe(viewLifecycleOwner) {
            setHistory(it)
        }

        binding.ClearHistoryButton.setOnClickListener{
            AlertDialog.Builder(activity).apply {
                setTitle("Delete Warning")
                setMessage("Are you sure?")
                setPositiveButton("Yes") { _, _ ->
                    deleteHistory(userInfo.token)
                }
                create()
                show()
            }

        }

        val binding = binding.recyclerView
        binding.layoutManager = LinearLayoutManager(activity)
    }

    @SuppressLint("SetTextI18n")
    private fun setData(data: User){
        with(binding){
            val fullname = data.fullname
            tvName.text = "Hi, $fullname"
            tvEmail.text = data.email
        }
    }

    private fun setHistory(histories: List<ListHistoryItem>) {
        val listHistory = ArrayList<ListHistoryItem>()
        for (history in histories) {
            listHistory.clear()
            listHistory.addAll(histories)
        }
        val adapter = ListHistoryAdapter(listHistory)
        binding.recyclerView.adapter = adapter
        adapter.setOnItemClickCallback(object : ListHistoryAdapter.OnItemClickCallback {
            override fun onItemClicked(data: ListHistoryItem) {
                showPlace(data)
            }
        })
    }

    @Suppress("DEPRECATION")
    private fun deleteHistory(token: String){
        profileViewModel.deleteVisitHistory(token)
        profileViewModel.deleteResponse.observe(viewLifecycleOwner){
            Toast.makeText(
                activity,
                it.message,
                Toast.LENGTH_SHORT
            ).show()
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            fragmentManager?.beginTransaction()?.detach(this)?.commitNow()
            fragmentManager?.beginTransaction()?.attach(this)?.commitNow()
        } else {
            fragmentManager?.beginTransaction()?.detach(this)?.attach(this)?.commit()
        }
    }


    private fun showPlace(data: ListHistoryItem) {
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