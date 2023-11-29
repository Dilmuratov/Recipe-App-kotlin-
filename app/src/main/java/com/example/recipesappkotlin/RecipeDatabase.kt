package com.example.recipesappkotlin

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Recipe::class], version = 1)
abstract class RecipeDatabase : RoomDatabase() {
    companion object {
        private lateinit var INSTANCE: RecipeDatabase
        fun getInstance(context: Context): RecipeDatabase {
            if (Companion::INSTANCE.isInitialized.not()) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    RecipeDatabase::class.java,
                    "recipe.db"
                )
                    .allowMainThreadQueries()
                    .createFromAsset("recipe.db")
                    .build()
            }
            return INSTANCE
        }
    }

    abstract fun dao(): RecipeDao
}