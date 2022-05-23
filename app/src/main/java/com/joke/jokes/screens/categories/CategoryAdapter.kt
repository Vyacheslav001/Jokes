package com.joke.jokes.screens.categories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.`fun`.joke.jokes.R
import com.`fun`.joke.jokes.databinding.CategoryItemBinding

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.CategoryHolder>() {

    private var categoryList = ArrayList<Category>()

    inner class CategoryHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = CategoryItemBinding.bind(item)
        fun bind(category: Category) {
            binding.categoryItemTextView.text = category.category
            itemView.setOnClickListener{

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        return CategoryHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        holder.bind(categoryList[position])
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    fun setCategory(data: ArrayList<Category>){
        categoryList = data
        notifyDataSetChanged()
    }
}