package com.trasimus.adastrasample.communication.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.trasimus.adastrasample.models.Beer

@Dao
interface BeerDao {

    @get:Query("SELECT * FROM beers")
    val allBeers: LiveData<List<Beer>>

    @Query("SELECT * FROM beers where id = :id")
    fun getBeerById(id: Int): LiveData<Beer>

    @Query("DELETE FROM beers")
    fun deleteAllBeers()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBeer(beer: Beer)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBeers(beers: List<Beer>)

    @Delete
    fun deleteBeer(beer: Beer)

    @Update
    fun updateBeer(beer: Beer)
}