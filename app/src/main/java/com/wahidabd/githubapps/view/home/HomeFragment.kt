package com.wahidabd.githubapps.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
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

        mAdapter.setOnItemClick {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(it)
            findNavController().navigate(action)
        }

        binding.imgSearch.setOnClickListener {
            val q = binding.edtSearch.text.toString().trim()
            search(q)
        }

        viewModel.list()
        observableViewModel()
    }

    private fun search(q: String){
        viewModel.search(q)
        observableViewModel()
    }

    private fun observableViewModel() {
        viewModel.loading.observe(viewLifecycleOwner){
            if (it) {
                binding.loading.loading.visibility = View.VISIBLE
                binding.rvUser.visibility = View.GONE
            } else {
                binding.loading.loading.visibility = View.GONE
                binding.rvUser.visibility = View.VISIBLE
            }
        }
        viewModel.error.observe(viewLifecycleOwner){
            binding.error.error.visibility = View.VISIBLE
        }
        viewModel.list.observe(viewLifecycleOwner){res ->
            if (res.isEmpty()) binding.error.error.visibility = View.VISIBLE
            else mAdapter.setList(res)
        }
    }
}