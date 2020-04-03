package com.trasimus.adastrasample.screens.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.trasimus.adastrasample.communication.local.AppDatabase
import com.trasimus.adastrasample.communication.remote.beer.BeerRepository
import com.trasimus.adastrasample.models.Beer

class DetailFragmentViewModel : ViewModel() {

    fun beer(database: AppDatabase, id: Int): LiveData<Beer> {
        return database.beerModel().getBeerById(id)
    }
}