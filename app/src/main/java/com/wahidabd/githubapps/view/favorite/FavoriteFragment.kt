package com.wahidabd.githubapps.view.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.wahidabd.githubapps.databinding.FragmentFavoriteBinding
import com.wahidabd.githubapps.viewmodel.RoomViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RoomViewModel by viewModels()
    private lateinit var mAdapter: FavoriteAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAdapter = FavoriteAdapter()

        binding.rvUser.apply {
            adapter = mAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
            itemAnimator = DefaultItemAnimator()
        }

        mAdapter.setOnItemClick {
            val action = FavoriteFragmentDirections.actionFavoriteFragmentToDetailFragment(it)
            findNavController().navigate(action)
        }

        observableViewModel()
    }

    private fun observableViewModel() {
        viewModel.list()
        viewModel.list.observe(viewLifecycleOwner){res ->
            if (res.isEmpty()) binding.error.error.visibility = View.VISIBLE
            else mAdapter.setList(res)
        }
    }

}