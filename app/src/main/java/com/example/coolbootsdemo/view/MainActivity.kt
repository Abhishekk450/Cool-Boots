package com.example.coolbootsdemo.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coolbootsdemo.adapter.QuestionListAdapter
import com.example.coolbootsdemo.databinding.ActivityMainBinding
import com.example.coolbootsdemo.network.RetrofitService
import com.example.coolbootsdemo.viewmodel.QuestionsViewModel
import com.example.coolbootsdemo.viewmodelfactory.QuestionViewModelFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: QuestionsViewModel
    lateinit var questionListAdapter: QuestionListAdapter
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewModel()
        setupList()
        setupView()
    }

    private fun setupView() {
        lifecycleScope.launch {
            viewModel.listData.collect {
                questionListAdapter.submitData(it)
            }
        }
    }

    private fun setupList() {
        questionListAdapter = QuestionListAdapter()
        binding.recyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = questionListAdapter
        }

    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this,QuestionViewModelFactory(RetrofitService.getInstance()))[QuestionsViewModel::class.java]
    }
}