package ua.ma.superheroes

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Response
import java.io.IOException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView:RecyclerView = findViewById(R.id.recyclerView)

        val client = OkHttpClient()
        val request = okhttp3.Request.Builder()
            .url("https://akabab.github.io/superhero-api/api/all.json")
            .build()

        client.newCall(request).enqueue(object :Callback {
            override fun onFailure(call: Call, e: IOException) {
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val handler = Handler(Looper.getMainLooper())
                    handler.post{
                        val a = response.body?.string()
                        val heros = Gson().fromJson(a, Array<Hero>::class.java)

                        if (heros.isNotEmpty()){
                            val myAdapter = RecyclerViewAdapter(heros){
                                Toast.makeText(this@MainActivity, "$it clicked", Toast.LENGTH_SHORT).show()
                            }
                            recyclerView.adapter = myAdapter
                        }
                        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                    }
                }
            }
        })
    }
}

data class HerosResponse(val heros:Array<Hero>)
data class Hero(val name:String, val images:Images)
data class Images(val md:String)
