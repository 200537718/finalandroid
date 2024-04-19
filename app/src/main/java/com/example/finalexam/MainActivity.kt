package com.example.finalexam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.finalexam.ui.theme.AuthenticationActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Check if user is signed in (optional)
        val currentUser = auth.currentUser
        if (currentUser == null) {
            // Redirect to AuthenticationActivity if user is not signed in
            startActivity(Intent(this, AuthenticationActivity::class.java))
            finish() // Finish MainActivity to prevent user from navigating back
        }

        // Set up UI elements and listeners
        setupUI()
    }

    private fun setupUI() {
        // Set up UI elements and listeners here
        // For example, you can set up a button click listener to add a new bucket list item
        buttonAddNewItem.setOnClickListener {
            startActivity(Intent(this, AddBucketItemActivity::class.java))
        }
    }
}
