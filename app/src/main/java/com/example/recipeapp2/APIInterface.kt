package com.example.recipeapp2

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface APIInterface {
    @Headers("Content-Type: application/json")
    @GET("/recipes/")
    fun getRecipesList(): Call<List<Recipes.Recipe>>


    @Headers("Content-Type: application/json")
    @POST("/recipes/")
    fun addRecipe(@Body userData: Recipes.Recipe): Call<Recipes>


}