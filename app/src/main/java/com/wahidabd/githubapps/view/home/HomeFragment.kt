package com.wahidabd.githubapps.view.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.wahidabd.githubapps.databinding.FragmentHomeBinding
import com.wahidabd.githubapps.viewmodel.UsersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: UsersViewModel by viewModels()
    private lateinit var mAdapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
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

        observableViewModel()
    }

    private fun observableViewModel() {
        viewModel.list()
        viewModel.loading.observe(viewLifecycleOwner){
            if (it) binding.loading.loading.visibility = View.VISIBLE
            else binding.loading.loading.visibility = View.GONE
        }
        viewModel.error.observe(viewLifecycleOwner){
            binding.error.error.visibility = View.VISIBLE
        }
        viewModel.list.observe(viewLifecycleOwner){res ->
            if (res != null) {
                mAdapter.setList(res)
            }
        }
    }
}