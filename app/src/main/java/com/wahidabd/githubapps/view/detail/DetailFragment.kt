package com.wahidabd.githubapps.view.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import com.wahidabd.githubapps.R
import com.wahidabd.githubapps.core.currentDate
import com.wahidabd.githubapps.core.mySetText
import com.wahidabd.githubapps.core.setImage
import com.wahidabd.githubapps.data.model.FavoriteData
import com.wahidabd.githubapps.data.model.User
import com.wahidabd.githubapps.databinding.FragmentDetailBinding
import com.wahidabd.githubapps.viewmodel.RoomViewModel
import com.wahidabd.githubapps.viewmodel.UsersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: UsersViewModel by viewModels()
    private val roomViewModel: RoomViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()

    private var favoriteData = FavoriteData("")
    private var status = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imgBack.setOnClickListener { findNavController().navigateUp() }
        binding.imgFollow.setOnClickListener {
            if (status){
                deleteFavorite(args.login)
                Snackbar.make(it, "User deleted!", Snackbar.LENGTH_SHORT).show()
            }else{
                insertFavorite(favoriteData)
                Snackbar.make(it, "User added!", Snackbar.LENGTH_SHORT).show()
            }
        }

        val pager = FollowViewPagerAdapter(requireParentFragment(), args.login)
        binding.viewPager.adapter = pager

        val tab = listOf("Followers", "Following")
        TabLayoutMediator(binding.tabLayout, binding.viewPager){title, position ->
            title.text = tab[position]
        }.attach()

        checkFavorite(args.login)
        observableViewModel()
    }

    private fun observableViewModel() {
        viewModel.user(args.login)
        viewModel.loading.observe(viewLifecycleOwner){res ->
            if (res) {
                binding.loading.loading.visibility = View.VISIBLE
                binding.linearView.visibility = View.GONE
            } else {
                binding.loading.loading.visibility = View.GONE
                binding.linearView.visibility = View.VISIBLE
            }
        }
        viewModel.error.observe(viewLifecycleOwner){
            binding.error.error.visibility = View.VISIBLE
            binding.linearView.visibility = View.GONE
        }
        viewModel.user.observe(viewLifecycleOwner){res ->
            setUser(res)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setUser(data: User){
        with(binding){
            imgAvatar.setImage(data.avatar_url)
            tvName.text = data.name
            tvUsername.text = data.login
            tvBio.mySetText(data.bio)
            tvCompany.mySetText(data.company)
            tvLocation.mySetText(data.location)
            tvBlog.mySetText(data.blog)
            tvFollow.text = "${data.followers} followers \u2022 ${data.following} following"

            favoriteData = FavoriteData(
                login = args.login,
                name = data.name,
                avatar = data.avatar_url,
                updated_at = currentDate()
            )
        }
    }

    private fun insertFavorite(data: FavoriteData){
        roomViewModel.insert(data)
        status = true
        checkFavorite(data.login)
    }

    private fun deleteFavorite(login: String){
        roomViewModel.delete(login)
        status = false
        checkFavorite(login)
    }

    private fun checkFavorite(login: String){
        roomViewModel.detail(login)
        roomViewModel.favorite.observe(viewLifecycleOwner){ res ->
            status = res != null
            if (status) {
                binding.imgFollow.setImageDrawable(
                    ContextCompat.getDrawable(requireContext(), R.drawable.ic_follow)
                )
            }else{
                binding.imgFollow.setImageDrawable(
                    ContextCompat.getDrawable(requireContext(), R.drawable.ic_un_follow)
                )
            }
        }
    }
}