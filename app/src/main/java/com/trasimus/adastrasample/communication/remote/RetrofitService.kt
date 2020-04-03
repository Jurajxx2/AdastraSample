package com.trasimus.adastrasample.communication.remote

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {
    companion object{
        private val retrofit = Retrofit.Builder()
            .baseUrl("https://api.punkapi.com/v2/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        fun <S> cteateService(serviceClass: Class<S>): S {
            return retrofit.create(serviceClass)
        }
    }
}