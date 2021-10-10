package com.example.recipeapp2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.core.view.isVisible
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var titleEditText: EditText
    lateinit var authorEditText: EditText
    lateinit var ingredientsEditText: EditText
    lateinit var instructionsEditText: EditText

    lateinit var saveRecipeButton: Button
    lateinit var viewRecipesButton: Button

    lateinit var progressBar: ProgressBar
    lateinit var progressBarTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar = findViewById(R.id.progressBar)
        progressBarTextView = findViewById(R.id.progressBarText)
        setProgressBar(false)

        titleEditText = findViewById(R.id.edt_title)
        authorEditText = findViewById(R.id.edt_author)
        ingredientsEditText = findViewById(R.id.edt_ingredients)
        instructionsEditText = findViewById(R.id.edt_instructions)

        saveRecipeButton = findViewById(R.id.btn_saveRecipe)
        viewRecipesButton = findViewById(R.id.btn_viewRecipe)


        saveRecipeButton.setOnClickListener {
            var title = titleEditText.text.toString()
            var author = authorEditText.text.toString()
            var ingredients = ingredientsEditText.text.toString()
            var instructions = instructionsEditText.text.toString()

            if(title.trim().length >=2 && author.trim().length >=2 && ingredients.trim().length >=2 && instructions.trim().length >=2){
                var newRecipe = Recipes.Recipe(title, author, ingredients, instructions)
                postRecipe(newRecipe, onResult = {
                    titleEditText.text.clear()
                    authorEditText.text.clear()
                    ingredientsEditText.text.clear()
                    instructionsEditText.text.clear()
                    Toast.makeText(applicationContext, "Save Success!", Toast.LENGTH_SHORT).show()

                })
            }else{
                Toast.makeText(this, "Please enter valid information", Toast.LENGTH_SHORT).show()
            }
        }


        viewRecipesButton.setOnClickListener {
            val intent = Intent(this, RecipesActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setProgressBar(visibility: Boolean) {
        progressBar.isVisible = visibility
        progressBarTextView.isVisible = visibility
    }

    private fun postRecipe(recipe: Recipes.Recipe, onResult: ()-> Unit) {
        setProgressBar(true)
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        if (apiInterface != null) {
            apiInterface.addRecipe(recipe).enqueue(object : Callback<Recipes> {
                override fun onResponse(call: Call<Recipes>, response: Response<Recipes>) {
                    setProgressBar(false)
                    onResult()

                }
                override fun onFailure(call: Call<Recipes>, t: Throwable) {
                    setProgressBar(false)
                    onResult()
                }
            })
        }

    }
}