package com.adrian.githubapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.adrian.githubapp.R
import com.adrian.githubapp.adapter.FollowingAdapter
import com.adrian.githubapp.model.GithubUser
import com.adrian.githubapp.model.viewmodel.FollowingViewModel
import com.adrian.githubapp.ui.activity.DetailActivity

import com.adrian.githubapp.databinding.FragmentFollowingBinding

class FollowingFragment : Fragment() {
    private lateinit var binding: FragmentFollowingBinding
    private lateinit var followingViewModel: FollowingViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentFollowingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        followingViewModel = ViewModelProvider(this).get(FollowingViewModel::class.java)
        followingViewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressFollowing.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
        followingViewModel.following.observe(viewLifecycleOwner) { listFollowing ->
            setDataToFragment(listFollowing)
        }
        followingViewModel.getFollowing(arguments?.getString(DetailActivity.EXTRA_FRAGMENT).toString())
    }

    private fun setDataToFragment(listFollowing: List<GithubUser>) {
        binding.rvFollowing.layoutManager = LinearLayoutManager(requireContext())
        binding.rvFollowing.adapter = FollowingAdapter(listFollowing)
    }
}