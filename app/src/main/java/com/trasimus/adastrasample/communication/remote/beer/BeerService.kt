package com.trasimus.adastrasample.communication.remote.beer

import com.trasimus.adastrasample.models.Beer
import retrofit2.Call
import retrofit2.http.*

interface BeerService {

    @GET("beers")
    fun getBeers(): Call<List<Beer>>
}