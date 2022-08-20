package com.wahidabd.githubapps.view.detail.follow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.wahidabd.githubapps.databinding.FragmentFollowingBinding
import com.wahidabd.githubapps.view.detail.DetailFragmentDirections
import com.wahidabd.githubapps.view.home.UserAdapter
import com.wahidabd.githubapps.viewmodel.FollowViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FollowingFragment : Fragment() {

    private var _binding: FragmentFollowingBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FollowViewModel by viewModels()
    private lateinit var mAdapter: UserAdapter

    private var username: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            username = it.getString(USERNAME)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFollowingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAdapter = UserAdapter()
        binding.rvUser.apply {
            adapter = mAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
            itemAnimator = DefaultItemAnimator()
        }

        mAdapter.setOnItemClick {
            val action = DetailFragmentDirections.actionDetailFragmentSelf(it)
            findNavController().navigate(action)
        }

        observableViewModel()
    }

    private fun observableViewModel() {
        viewModel.following(username.toString())
        viewModel.loading.observe(viewLifecycleOwner){
            if (it) binding.loading.loading.visibility = View.VISIBLE
            else binding.loading.loading.visibility = View.GONE
        }
        viewModel.error.observe(viewLifecycleOwner){
            binding.error.error.visibility = View.VISIBLE
        }
        viewModel.list.observe(viewLifecycleOwner){res ->
            if (res.isEmpty()) binding.error.error.visibility = View.VISIBLE
            else mAdapter.setList(res)
        }
    }

    companion object {
        fun newInstance(username: String) =
            FollowingFragment().apply {
                arguments = Bundle().apply {
                    putString(USERNAME, username)
                }
            }
        private const val USERNAME = "USERNAME"
    }
}