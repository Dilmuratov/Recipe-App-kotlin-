package com.example.recipesappkotlin

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe")
data class Recipe(
    @PrimaryKey
    @ColumnInfo(name = "uid")
    var uid: Int,
    @ColumnInfo(name = "img")
    var img: String,
    @ColumnInfo(name = "tittle")
    var tittle: String,
    @ColumnInfo(name = "des")
    var des: String,
    @ColumnInfo(name = "ing")
    var ing: String,
    @ColumnInfo(name = "category")
    var category: String
)