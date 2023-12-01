package com.example.recipesappkotlin

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_recipe.*

class RecipeActivity : AppCompatActivity() {
    private lateinit var img: ImageView
    private lateinit var backBtn: ImageView
    private lateinit var overlay: ImageView
    private lateinit var scroll: ImageView
    private lateinit var zoomImage: ImageView
    private lateinit var txt: TextView
    private lateinit var ing: TextView
    private lateinit var time: TextView
    private lateinit var steps: TextView
    private lateinit var ingList: Array<String>
    private lateinit var stepBtn: Button
    private lateinit var ing_btn: Button
    private var isImgCrop = false
    private lateinit var scrollView: ScrollView
    private lateinit var scrollView_step: ScrollView


    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("CheckResult", "SetTextI18n", "ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)


        // Find views
        img = findViewById(R.id.recipe_img)
        txt = findViewById(R.id.tittle)
        ing = findViewById(R.id.ing)
        time = findViewById(R.id.time)
        stepBtn = findViewById(R.id.steps_btn)
        ing_btn = findViewById(R.id.ing_btn)
        backBtn = findViewById(R.id.back_btn)
        steps = findViewById(R.id.steps_txt)
        scrollView = findViewById(R.id.ing_scroll)
        scrollView_step = findViewById(R.id.steps)
        overlay = findViewById(R.id.image_gradient)
        scroll = findViewById(R.id.scroll)
        zoomImage = findViewById(R.id.zoom_image)


        // Load recipe image from link

        // Load recipe image from link
        Glide.with(applicationContext).load(intent.getStringExtra("img"))
            .into(img)
        // Set recipe title
        // Set recipe title
        txt.text = intent.getStringExtra("tittle")

        // Set recipe ingredients

        // Set recipe ingredients
        ingList = intent.getStringExtra("ing")!!.split("\n".toRegex()).toTypedArray()
        // Set time
        // Set time
        time.text = ingList[0]


        for (i in 1 until ingList.size) {
            ing.text = "${ing.text}\n🟢${ingList[i]}".trimIndent()
            /*if(ingList[i].startsWith(" ")){
                ing.setText(ing.getText()+"\uD83D\uDFE2  "+ingList[i].trim().replaceAll("\\s{2,}", " ")+"\n");
            }else{

            }*/
        }
        // Set recipe steps
        // Set recipe steps
        steps.text = intent.getStringExtra("des")
        // steps.setText(Html.fromHtml(getIntent().getStringExtra("des")));

        // steps.setText(Html.fromHtml(getIntent().getStringExtra("des")));
        stepBtn.background = null

        stepBtn.setOnClickListener { v: View? ->
            stepBtn.setBackgroundResource(R.drawable.btn_ing)
            stepBtn.setTextColor(getColor(R.color.white))
            ing_btn.background = null
            ing_btn.setTextColor(getColor(R.color.black))
            scrollView.visibility = View.GONE
            scrollView_step.visibility = View.VISIBLE
        }

        ing_btn.setOnClickListener { v: View? ->
            ing_btn.setBackgroundResource(R.drawable.btn_ing)
            ing_btn.setTextColor(getColor(R.color.white))
            stepBtn.background = null
            stepBtn.setTextColor(getColor(R.color.black))
            scrollView.visibility = View.VISIBLE
            scrollView_step.visibility = View.GONE
        }


        // Full recipe image button


        // Full recipe image button
        zoomImage.setOnClickListener { view: View? ->
            if (!isImgCrop) {
                img.scaleType = ImageView.ScaleType.FIT_CENTER
                overlay.imageAlpha = 0
                Glide.with(applicationContext).load(intent.getStringExtra("img"))
                    .into(img)
                isImgCrop = true
            } else {
                img.scaleType = ImageView.ScaleType.CENTER_CROP
                overlay.imageAlpha = 255
                Glide.with(applicationContext).load(intent.getStringExtra("img"))
                    .into(img)
                isImgCrop = false
            }
        }


        // Exit activity


        // Exit activity
        backBtn.setOnClickListener { v: View? -> finish() }


    }
}