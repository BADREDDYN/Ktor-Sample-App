package com.ktor.testapp.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.ktor.testapp.R
import com.ktor.testapp.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    //Binding
    private lateinit var binding: ActivityMainBinding

    //ViewModel
    private val vm by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Reload the posts
        binding.btnReload.setOnClickListener {
            vm.getPosts()
        }

        lifecycleScope.launchWhenStarted {
            vm.postsMutableStateFlow.collectLatest { postsList ->
                withContext(Dispatchers.Main) {
                    postsList.forEach { post ->
                        "${post.id} ${post.userId} ${post.title} ${post.body}".shortSnackBar()
                        delay(1000)
                    }
                }
            }
        }


    }

    private fun String.shortSnackBar() {
        Snackbar.make(binding.root, this, Snackbar.LENGTH_SHORT).show()
    }
}