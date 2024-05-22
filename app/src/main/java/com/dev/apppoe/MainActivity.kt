package com.dev.apppoe

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.dev.apppoe.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.github.kittinunf.fuel.Fuel
import com.google.ai.client.generativeai.type.content
import com.google.gson.reflect.TypeToken


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
        fetchLeagues()
    }
    private fun fetchLeagues() {
        val url = "https://api.pathofexile.com/leagues"
        Fuel.get(url).response { _, _, result ->
            result.fold(
                { data ->
                    val content = String(data)
                    val type = object : TypeToken<List<League>>() {}.type
                    val leagues: List<League> = Gson().fromJson(content, type)
                    runOnUiThread {
                        setupRecyclerView(leagues)
                    }
                },
                { error ->
                    // Manejar el error
                    error.printStackTrace()
                }
            )
        }
    }

    private fun setupRecyclerView(leagues: List<League>) {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = LeagueAdapter(this, leagues)
    }
}