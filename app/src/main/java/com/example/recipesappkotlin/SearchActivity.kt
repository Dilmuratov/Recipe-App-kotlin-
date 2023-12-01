package com.example.recipesappkotlin

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.example.recipesappkotlin.databinding.ActivitySearchBinding
import java.util.*

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    private lateinit var dao: RecipeDao
    private lateinit var dataList: MutableList<Recipe>
    private lateinit var adapter: SearchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListeners()

        binding.search.requestFocus()

        setListToAdapter()

    }

    private fun setListToAdapter() {
        dao = RecipeDatabase.getInstance(this@SearchActivity.applicationContext).dao()
        dataList = mutableListOf()
        for (recipe in dao.getAll()) {
            if (recipe.category.contains("Popular")) {
                dataList.add(recipe)
            }
        }

        adapter = SearchAdapter(dataList, this@SearchActivity.applicationContext)
        binding.rcview.adapter = adapter
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initListeners() {
        binding.backToHome.setOnClickListener {
            finish()
        }

        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        binding.rcview.setOnTouchListener { _, _ ->
            imm.hideSoftInputFromWindow(binding.search.windowToken, 0)
            return@setOnTouchListener false
        }

        binding.search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //
            }

            override fun afterTextChanged(p0: Editable?) {
                if (p0.toString() != "") { // Search if new alphabet added
                    filter(p0.toString())
                }
            }
        })

    }

    fun filter(text: String) {
        val filterList: MutableList<Recipe> = ArrayList<Recipe>()
        for (i in dataList.indices) { // Loop for check searched item in recipe list
            if (dataList[i].tittle.lowercase(Locale.ROOT).contains(text.lowercase())) {
                filterList.add(dataList[i])
            }
        }

        // Update search recyclerView with new item
        adapter.filterList(filterList)
    }
}