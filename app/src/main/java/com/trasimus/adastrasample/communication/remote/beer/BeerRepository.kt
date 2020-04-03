package com.trasimus.adastrasample.communication.remote.beer

import androidx.lifecycle.MutableLiveData
import com.trasimus.adastrasample.communication.local.AppDatabase
import com.trasimus.adastrasample.communication.remote.RetrofitService
import com.trasimus.adastrasample.models.Beer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BeerRepository {
    private val beerService: BeerService = RetrofitService.cteateService(BeerService::class.java)

    fun getBeers(database: AppDatabase): MutableLiveData<List<Beer>> {
        val result = MutableLiveData<List<Beer>>()
        beerService.getBeers().enqueue(object : Callback<List<Beer>> {

            override fun onResponse(call: Call<List<Beer>>?, response: Response<List<Beer>>) {
                if (response.isSuccessful) {
                    result.setValue(response.body())
                    Thread(Runnable{
                        response.body()?.let {
                            database.beerModel().insertBeers(it)
                        }
                    }).start()
                }
            }

            override fun onFailure(call: Call<List<Beer>>?, t: Throwable?) {
                result.value = null
            }
        })
        return result
    }

    companion object {

        private var beerRepository: BeerRepository? = null

        val instance: BeerRepository
            get() {
                if (beerRepository == null) {
                    beerRepository = BeerRepository()
                }
                return beerRepository as BeerRepository
            }
    }
}