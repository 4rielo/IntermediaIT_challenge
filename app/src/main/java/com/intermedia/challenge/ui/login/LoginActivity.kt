package com.intermedia.challenge.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.intermedia.challenge.ui.main.MainScreenActivity
import com.intermedia.challenge.R

class LoginActivity : AppCompatActivity() {

    //private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //auth = Firebase.auth
    }

    override fun onResume() {
        super.onResume()
        startFirebaseAuth()
    }

    private fun startFirebaseAuth() {
        // TODO complete using Firebase Auth UI
        //val currentUser = auth.currentUser
        //if(currentUser != null){
            startActivity(Intent(this, MainScreenActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            })
        //}
        // TODO provisional
    }

}