package com.example.recipesappkotlin

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room

class CategoryActivity : AppCompatActivity() {
    private lateinit var recview: RecyclerView
    private var connected = false
    private var dataFinal = mutableListOf<Recipe>()
    private lateinit var back: ImageView
    private lateinit var tittle: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)


        // Find views

        // Find views
        back = findViewById(R.id.imageView2)
        tittle = findViewById(R.id.tittle)
        recview = findViewById<View>(R.id.recview) as RecyclerView

        // Set layout manager to recyclerView

        // Set layout manager to recyclerView
        recview.layoutManager = LinearLayoutManager(this)

        // Set recipe category title

        // Set recipe category title
        tittle.text = intent.getStringExtra("tittle")

        // Get database

        // Get database
        val db = RecipeDatabase.getInstance(applicationContext)
        val userDao: RecipeDao = db.dao()


        // Get all recipes from database

        // Get all recipes from database
        val recipes: List<Recipe> = userDao.getAll()

        // Filter category from recipes

        // Filter category from recipes
        for (i in recipes.indices) {
            if (recipes[i].category.contains(intent.getStringExtra("Category")!!)) {
                dataFinal.add(recipes[i])
            }
        }

        // Set category list to adapter

        // Set category list to adapter
        val adapter = Adaptar(dataFinal, applicationContext)
        recview.adapter = adapter

        back.setOnClickListener { v: View? -> finish() }

    }
}