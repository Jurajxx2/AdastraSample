package com.trasimus.adastrasample.screens.list

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.trasimus.adastrasample.communication.local.AppDatabase
import com.trasimus.adastrasample.communication.remote.beer.BeerRepository
import com.trasimus.adastrasample.models.Beer

class ListFragmentViewModel() : ViewModel() {

    fun beers(database: AppDatabase): LiveData<List<Beer>> {
        saveBeers(database)
        return database.beerModel().allBeers
    }

    private fun saveBeers(database: AppDatabase) {
        BeerRepository().getBeers(database)
    }
}