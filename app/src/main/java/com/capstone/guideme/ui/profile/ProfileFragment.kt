package com.capstone.guideme.ui.profile

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.capstone.guideme.MainActivity
import com.capstone.guideme.databinding.FragmentProfileBinding
import com.capstone.guideme.model.User


class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val profileViewModel by viewModels<ProfileViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val userId = arguments?.getString(MainActivity.EXTRA_USERID).toString()
        Log.e("mainAct", userId)

//        profileViewModel.getUserDetail(
//            userId
//        )
        profileViewModel.user.observe(viewLifecycleOwner) {
            setData(it)
        }

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    private fun setData(data: User){
        with(binding){
            val fullname = data.fullname
            tvName.text = "Hi, $fullname"
            tvEmail.text = data.email
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}