package com.intermedia.challenge.ui.main

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.intermedia.challenge.R
import com.intermedia.challenge.databinding.ActivityMainScreenBinding

class MainScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainScreenBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
            setCustomView(R.layout.support_action_bar)
        }
        navController = findNavController(R.id.nav_host_fragment)
        binding.bottomNavView.apply {
            setupWithNavController(navController)
            itemIconTintList = null
        }
    }

}