package com.example.recipeapp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecipesActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView

    lateinit var progressBar: ProgressBar
    lateinit var progressBarTextView: TextView

    var recipesList = arrayListOf<Recipes.Recipe>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipes)

        progressBar = findViewById(R.id.progressBar2)
        progressBarTextView = findViewById(R.id.progressBarText2)
        setProgressBar(false)

        //initialize recyclerView
        recyclerView = findViewById(R.id.rv_recipes)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = RecipesAdapter(recipesList)

        CoroutineScope(Dispatchers.IO).launch {
            getRecipesList()
        }
    }

    private fun getRecipesList() {
        setProgressBar(true)
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        if (apiInterface != null) {
            apiInterface.getRecipesList()?.enqueue(object : Callback<List<Recipes.Recipe>> {
                override fun onResponse(
                    call: Call<List<Recipes.Recipe>>,
                    response: Response<List<Recipes.Recipe>>
                ) {
                    setProgressBar(false)
                    Log.d(
                        "GET Response:",
                        response.code().toString() + " " + response.message()
                    )
                    for (User in response.body()!!) {
                        recipesList.add(User)
                    }
                    recyclerView.adapter!!.notifyDataSetChanged()
                }

                override fun onFailure(call: Call<List<Recipes.Recipe>>, t: Throwable) {
                    setProgressBar(false)
                    Toast.makeText(this@RecipesActivity, "" + t.message, Toast.LENGTH_SHORT)
                        .show()
                }
            })
        }
    }

    private fun setProgressBar(visibility: Boolean) {
        progressBar.isVisible = visibility
        progressBarTextView.isVisible = visibility
    }
}