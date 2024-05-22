package com.dev.apppoe

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dev.apppoe.databinding.ItemLeagueBinding
import com.google.gson.Gson

class LeagueAdapter(private val context: Context, private val leagues: List<League>) :
    RecyclerView.Adapter<LeagueAdapter.LeagueViewHolder>() {

    inner class LeagueViewHolder(val binding: ItemLeagueBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLeagueBinding.inflate(inflater, parent, false)
        return LeagueViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) {
        val league = leagues[position]
        holder.binding.leagueName.text = league.id
        holder.binding.leagueDescription.text = league.description

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("league", Gson().toJson(league))
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return leagues.size
    }
}
