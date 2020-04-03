package com.trasimus.adastrasample.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "beers")
class Beer(
    @ColumnInfo(name = "tagline") var tagline: String,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "image_url") var image_url: String,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "first_brewed") var first_brewed: String,
    @PrimaryKey(autoGenerate = false) var id: Int = 0) {

}