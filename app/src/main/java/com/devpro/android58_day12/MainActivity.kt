package com.devpro.android58_day12

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import com.devpro.android58_day12.databinding.ActivityMainBinding
import com.devpro.android58_day12.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Khởi tạo DataBinding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        // Cho phép DataBinding quan sát LiveData theo lifecycle của Activity
        binding.lifecycleOwner = this
        // Gắn ViewModel vào layout
        binding.vm = viewModel
    }
}