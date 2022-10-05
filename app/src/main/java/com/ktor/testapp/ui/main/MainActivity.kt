package com.ktor.testapp.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.ktor.testapp.R
import com.ktor.testapp.data.remote.PostsService
import com.ktor.testapp.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    //Binding
    private lateinit var binding :ActivityMainBinding

    //
    private val service = PostsService.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch(Dispatchers.IO) {
            val returned = async {
                service.getPosts()
            }

            withContext(Dispatchers.Main) {
                val postsList = returned.await()
                postsList.forEach { post ->
                    "${post.id} ${post.userId} ${post.title} ${post.body}".shortSnackBar()
                    delay(1000)
                }
            }
        }




    }

    private fun String.shortSnackBar() {
        Snackbar.make(binding.root, this, Snackbar.LENGTH_SHORT).show()
    }
}