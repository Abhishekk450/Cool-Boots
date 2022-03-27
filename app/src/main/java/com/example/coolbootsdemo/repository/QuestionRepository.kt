package com.example.coolbootsdemo.repository

import androidx.paging.PagingData
import com.example.coolbootsdemo.const.AppConstant
import com.example.coolbootsdemo.model.Data
import com.example.coolbootsdemo.network.RetrofitService
import kotlinx.coroutines.flow.Flow

class QuestionRepository constructor(private val retrofitService: RetrofitService) {

    suspend fun getAllQuestions(currentLoadingPageKey:Int) = retrofitService.getAllQuestions(AppConstant.USER_TOKEN, AppConstant.HANDSHAKE,16, currentLoadingPageKey)
}