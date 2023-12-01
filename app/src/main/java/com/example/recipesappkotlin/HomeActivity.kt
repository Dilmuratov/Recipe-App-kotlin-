package com.example.recipesappkotlin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room.databaseBuilder
import com.example.recipesappkotlin.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var rvAdapter: PopularAdapter
    private lateinit var dao: RecipeDao
    private lateinit var dataList: MutableList<Recipe>
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecyclerView()

        initListeners()
    }

    private fun initListeners() {
        binding.editTextText.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setUpRecyclerView() {
        dao = RecipeDatabase.getInstance(this@HomeActivity.applicationContext).dao()
        dataList = mutableListOf()
        for (recipe in dao.getAll()) {
            if (recipe.category.contains("Popular")) {
                dataList.add(recipe)
            }
        }

        Log.d("TAG", "setUpRecyclerView: ${dataList.size}")

        rvAdapter = PopularAdapter(dataList, this@HomeActivity.applicationContext)

        binding.rvPopular.adapter = rvAdapter
    }
}