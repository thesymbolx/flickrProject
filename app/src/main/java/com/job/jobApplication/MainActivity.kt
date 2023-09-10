package com.job.jobApplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.job.jobApplication.ui.MainActivityScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        getApiKey()
        super.onCreate(savedInstanceState)
        setContent {
            MainActivityScreen()
        }
    }

    private fun getApiKey() {

        // Write a message to the database
        val database = Firebase.database
        val myRef = database.getReference("message")

        myRef.get().addOnSuccessListener {
            Log.d("booboo", "$it")
        }
    }
}