package com.example.madlevel4task2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.game_fragment.view.*

class PortalAdapter(private val portals: List<Game>) : RecyclerView.Adapter<PortalAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun databind(game: Game) {
            when (game.result) {
                "WIN" -> {
                    itemView.tvResult.text = "You win"
                }
                "LOSE" -> {
                    itemView.tvResult.text = "Computer wins"
                }
                else -> {
                    itemView.tvResult.text = game.result
                }
            }
            itemView.tvTime.text = game.date.toString()
            when (game.cMove.toInt()) {
                1 -> itemView.ivCResult.setImageResource(R.drawable.paper)
                2 -> itemView.ivCResult.setImageResource(R.drawable.rock)
                3 -> itemView.ivCResult.setImageResource(R.drawable.scissors)
            }
            when (game.pMove.toInt()) {
                1 -> itemView.ivPResult.setImageResource(R.drawable.paper)
                2 -> itemView.ivPResult.setImageResource(R.drawable.rock)
                3 -> itemView.ivPResult.setImageResource(R.drawable.scissors)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PortalAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.game_fragment, parent, false))
    }

    override fun getItemCount(): Int {
        return portals.size
    }

    override fun onBindViewHolder(holder: PortalAdapter.ViewHolder, position: Int) {
        holder.databind(portals[position])
    }
}