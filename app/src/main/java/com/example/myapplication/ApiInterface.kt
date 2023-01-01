package com.example.myapplication

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


// api call
interface ApiInterface {
    //method GET random photos from apis
        @GET("photos/random?client_id=WkRJU_9IjK1TaJOSuDwLLyvQnVOyDrenYJZnP5a58_g&count=16")

       open fun getDataImage(): Call<MutableList<Data>?>?
}
class RetrofitInstance {
    companion object {
        //api url
        val BASE_URL: String = "https://api.unsplash.com/"

        val interceptor: HttpLoggingInterceptor =
            HttpLoggingInterceptor().apply {
                this.level = HttpLoggingInterceptor.Level.BODY
            }
        val client: OkHttpClient = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
        }.build()
        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())

                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        }
    }
}