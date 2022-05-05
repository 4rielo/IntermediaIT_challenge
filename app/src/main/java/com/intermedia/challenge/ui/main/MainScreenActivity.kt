package com.intermedia.challenge.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.intermedia.challenge.R
import com.intermedia.challenge.databinding.ActivityMainScreenBinding
import com.intermedia.challenge.ui.character_detail.CharacterDetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainScreenBinding
    private val mainViewModel: MainViewModel by viewModel()

    companion object {
        internal const val SELECTED_CHARACTER = "selected_character"
    }

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
            setCustomView(R.layout.support_action_bar)
            val closeButton = findViewById<AppCompatImageView>(R.id.ivCloseButton)
            closeButton.isVisible = false
        }

        navController = findNavController(R.id.nav_host_fragment)
        binding.bottomNavView.apply {
            setupWithNavController(navController)
            itemIconTintList = null
        }

        setObservers()
    }

    private fun setObservers(){
        observeGoToCharacterDetail()
    }

    private fun observeGoToCharacterDetail(){
        mainViewModel.goToCharacterDetail.observe(this){ selectedCharacter ->
            val intent = Intent(this, CharacterDetailActivity::class.java)
            intent.putExtra(SELECTED_CHARACTER, selectedCharacter)
            startActivity(intent)
        }
    }

}