package com.example.recipesappkotlin

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipesappkotlin.databinding.SearchListBinding

class SearchAdapter(var data: List<Recipe>, var context: Context) :
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    inner class ViewHolder(private var binding: SearchListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val item = data[position]
            Glide.with(context).load(item.img).into(binding.searchImg)
            binding.searchTxt.text = item.tittle

            binding.root.setOnClickListener { v ->
                val intent = Intent(context, RecipeActivity::class.java)
                intent.putExtra("img", item.img)
                intent.putExtra("tittle", item.tittle)
                intent.putExtra("des", item.des)
                intent.putExtra("ing", item.ing)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        SearchListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount() = data.size

    @SuppressLint("NotifyDataSetChanged")
    fun filterList(filterList: List<Recipe>) {
        data = filterList
        notifyDataSetChanged()
    }
}