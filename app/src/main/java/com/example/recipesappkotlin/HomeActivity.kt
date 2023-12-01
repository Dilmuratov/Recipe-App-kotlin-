package com.example.recipesappkotlin

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
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


        // Category buttons- start new activity with intent method "start"
        binding.salad.setOnClickListener(View.OnClickListener { v: View? ->
            start(
                "Salad", "Salad"
            )
        })
        binding.mainDish.setOnClickListener(View.OnClickListener { v: View? ->
            start(
                "Dish", "Main dish"
            )
        })
        binding.Drinks.setOnClickListener(View.OnClickListener { v: View? ->
            start(
                "Drinks", "Drinks"
            )
        })
        binding.Deserts.setOnClickListener(View.OnClickListener { v: View? ->
            start(
                "Desserts", "Dessert"
            )
        })

        binding.imageView.setOnClickListener {
            showBottomSheet()
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

    private fun start(p: String?, tittle: String?) {
        val intent = Intent(this@HomeActivity, CategoryActivity::class.java)
        intent.putExtra("Category", p)
        intent.putExtra("tittle", tittle)
        startActivity(intent)
    }

    // Create a bottom dialog for privacy policy and about
    private fun showBottomSheet() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.bottom_sheet)
        val privayPolicy = dialog.findViewById<LinearLayout>(R.id.privacy_policy)
        val abtDev = dialog.findViewById<LinearLayout>(R.id.about_dev)
        privayPolicy.setOnClickListener { v: View? ->
            val intent = Intent(Intent.ACTION_VIEW)
//            intent.data = Uri.parse("https://makesdigi.com/recipeApp/privacy.html")
            intent.data = Uri.parse("https://t.me/Nodirbek_01_05")
            startActivity(intent)
        }
        abtDev.setOnClickListener { v: View? ->
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://t.me/Nodirbek_01_05")
            startActivity(intent)
        }
        dialog.show()
        dialog.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window!!.attributes.windowAnimations = R.style.DialogAnimation
        dialog.window!!.setGravity(Gravity.BOTTOM)
    }
}