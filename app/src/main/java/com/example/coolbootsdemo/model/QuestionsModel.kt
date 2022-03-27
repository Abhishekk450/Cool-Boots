package com.example.coolbootsdemo.model

data class QuestionsModel(
    val message: String,
    val response: Response,
    val status: String,
    val status_code: Int
)