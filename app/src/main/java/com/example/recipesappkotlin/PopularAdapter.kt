package com.example.recipesappkotlin

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipesappkotlin.databinding.PopularRvItemBinding

class PopularAdapter(var dataList: List<Recipe>, var context: Context) :
    RecyclerView.Adapter<PopularAdapter.ViewHolder>() {
    inner class ViewHolder(var binding: PopularRvItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val data = dataList[position]
            Glide.with(context).load(data.img).into(binding.popularImg)
            binding.popularTxt.text = data.tittle
            binding.popularTime.text =
                data.ing.split("\n".toRegex()).dropLastWhile { it.isEmpty() }
                    .toTypedArray()[0]

            binding.popularImg.setOnClickListener {
                val intent = Intent(context, RecipeActivity::class.java)
                intent.putExtra("img", data.img)
                intent.putExtra("tittle", data.tittle)
                intent.putExtra("des", data.des)
                intent.putExtra("ing", data.ing)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        PopularRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount() = dataList.size
}