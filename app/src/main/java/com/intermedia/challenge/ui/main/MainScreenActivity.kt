package com.intermedia.challenge.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.intermedia.challenge.R
import com.intermedia.challenge.databinding.ActivityMainScreenBinding
import com.intermedia.challenge.ui.character_detail.CharacterDetailActivity
import com.intermedia.challenge.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_main_screen.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainScreenBinding
    private val mainViewModel: MainViewModel by viewModel()

    val user = Firebase.auth.currentUser

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

        binding.apply {
            viewModel = mainViewModel
            bottomNavView.setupWithNavController(navController)
            bottomNavView.itemIconTintList = null
            tvUserName.text = user?.displayName
        }
    }

    override fun onStart() {
        super.onStart()
        setObservers()
    }

    private fun setObservers(){
        observeGoToCharacterDetail()
        observeLogout()
    }

    private fun observeGoToCharacterDetail(){
        mainViewModel.goToCharacterDetail.observe(this){ selectedCharacter ->
            val intent = Intent(this, CharacterDetailActivity::class.java)
            intent.putExtra(SELECTED_CHARACTER, selectedCharacter)
            startActivity(intent)
        }
    }

    private fun observeLogout() {
        mainViewModel.logoutClicked.observe(this) {
            if(it) {
                logOut()
            }
        }
    }

    private fun logOut() {
        AuthUI.getInstance()
            .signOut(applicationContext)
            .addOnCompleteListener {
                val intent = Intent(applicationContext, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
    }

}