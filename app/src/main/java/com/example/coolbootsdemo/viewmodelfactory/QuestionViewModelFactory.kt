package com.example.coolbootsdemo.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.coolbootsdemo.network.RetrofitService
import com.example.coolbootsdemo.repository.QuestionRepository
import com.example.coolbootsdemo.viewmodel.QuestionsViewModel
import java.lang.IllegalArgumentException

class QuestionViewModelFactory constructor(private val retrofitService: RetrofitService):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(QuestionsViewModel::class.java)) {
            QuestionsViewModel(this.retrofitService) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}