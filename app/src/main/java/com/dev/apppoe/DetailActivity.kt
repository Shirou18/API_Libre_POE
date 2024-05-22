package com.dev.apppoe

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dev.apppoe.databinding.ActivityDetailBinding
import com.google.gson.Gson

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val leagueJson = intent.getStringExtra("league")
        val league = Gson().fromJson(leagueJson, League::class.java)

        with(binding) {
            leagueId.text = league.id
            leagueDescription.text = league.description
            leagueUrl.text = league.url
            leagueStartAt.text = league.startAt ?: "N/A"
            leagueEndAt.text = league.endAt ?: "N/A"
            leagueCategory.text = league.category.id
            leagueRules.text = buildRulesString(league.rules)
        }
    }
    private fun buildRulesString(rules: List<Rule>): String {
        val builder = StringBuilder()
        for (rule in rules) {
            builder.append("${rule.name}: ${rule.description}\n")
        }
        return builder.toString()
    }
}