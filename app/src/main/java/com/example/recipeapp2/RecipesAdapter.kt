package com.example.recipeapp2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recipe_item.view.*

class RecipesAdapter (private val recipesList: ArrayList<Recipes.Recipe>) :
    RecyclerView.Adapter<RecipesAdapter.RecipeViewHolder>() {
    class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.tv_title
        val authorTextView: TextView = itemView.tv_author
        val ingredientsTextView: TextView = itemView.tv_ingredients
        val instructionsTextView: TextView = itemView.tv_instructions
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.recipe_item,
            parent,
            false
        )
        return RecipeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val title = recipesList[position].title
        val author = recipesList[position].author
        val ingredients = recipesList[position].ingredients
        val instructions = recipesList[position].instructions
        holder.titleTextView.text = title
        holder.authorTextView.text = author
        holder.ingredientsTextView.text = ingredients
        holder.instructionsTextView.text = instructions
    }

    override fun getItemCount() = recipesList.size
}