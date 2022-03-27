package com.example.coolbootsdemo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.coolbootsdemo.datasource.PostDataSource
import com.example.coolbootsdemo.model.QuestionsModel
import com.example.coolbootsdemo.network.RetrofitService
import com.example.coolbootsdemo.repository.QuestionRepository
import kotlinx.coroutines.*

class QuestionsViewModel constructor(private val retrofitService: RetrofitService):ViewModel(){

    val errorMessage = MutableLiveData<String>()
    val questionList = MutableLiveData<QuestionsModel>()
    val loading = MutableLiveData<Boolean>()

    var job: Job? = null

    val listData = Pager(PagingConfig(pageSize = 6)) {
        PostDataSource(retrofitService)
    }.flow.cachedIn(viewModelScope)

    private val exceptionHandler =  CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }


    /*fun getAllQuestions() {

        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.postValue(true)

            val response = questionsRepository.getAllQuestions(listData)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    questionList.postValue(response.body())
                    loading.value = false
                } else {
                    onError("Error : ${response.message()}")
                }
            }
        }
    }*/

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}