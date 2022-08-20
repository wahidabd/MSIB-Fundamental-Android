package com.wahidabd.githubapps.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.wahidabd.githubapps.R
import com.wahidabd.githubapps.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHost = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        val navController = navHost.navController
        binding.bottomNav.setupWithNavController(navController)

        navController.addOnDestinationChangedListener{_, dest, _ ->
            when(dest.id){
                R.id.homeFragment,
                R.id.favoriteFragment
                    -> binding.bottomNav.visibility = View.VISIBLE
                else -> binding.bottomNav.visibility = View.GONE
            }
        }

    }
}