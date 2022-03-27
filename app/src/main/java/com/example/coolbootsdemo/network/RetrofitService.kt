package com.example.coolbootsdemo.network

import android.graphics.Movie
import com.example.coolbootsdemo.model.Movies
import com.example.coolbootsdemo.model.QuestionsModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface RetrofitService {

    @GET("teachers/question-banks")
    @Headers("Content-Type: application/json",
                      "Accept: application/json")
    suspend fun getAllQuestions(@Header(value = "Authorization")  userToken:String, @Header(value = "handshake") handshake:String,
      @Query("chapter_id") chapterId:Int, @Query("page") page:Int) : Response<QuestionsModel>

    /*@GET("movielist.json")
    suspend fun getAllMovies() : Response<List<Movies>>*/

    companion object {
        var retrofitService:RetrofitService? = null
        private val interceptor: HttpLoggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        private val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(2, TimeUnit.MINUTES)
            .readTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(2, TimeUnit.MINUTES)
            .addInterceptor(interceptor)
            .build()

        fun getInstance():RetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://skool.qureka.me/api/v1/")
                    //.baseUrl("https://howtodoandroid.com/")
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }

            return retrofitService!!
        }
    }

}