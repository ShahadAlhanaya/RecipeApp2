package com.example.recipeapp2

import com.google.gson.annotations.SerializedName

class Recipes {

    var recipes: List<Recipe>? = null

    class Recipe {

        @SerializedName("pk")//Primary Key
        var id: Int? = null

        @SerializedName("title")
        var title: String? = null

        @SerializedName("author")
        var author: String? = null

        @SerializedName("ingredients")
        var ingredients: String? = null

        @SerializedName("instructions")
        var instructions: String? = null


        constructor(title: String?, author: String?, ingredients: String?, instructions: String?) {
            this.title = title
            this.author = author
            this.ingredients = ingredients
            this.instructions = instructions
        }
    }
}