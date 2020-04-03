package com.trasimus.adastrasample.communication.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.trasimus.adastrasample.models.Beer

@Database(entities = [(Beer::class)], version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun beerModel(): BeerDao

    companion object {

        private var sInstance: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (sInstance == null) {
                sInstance = Room
                    .databaseBuilder(context.applicationContext, AppDatabase::class.java, "databaseX")
                    .build()
            }
            return sInstance!!
        }

        fun destroyInstance() {
            sInstance = null
        }


    }
}