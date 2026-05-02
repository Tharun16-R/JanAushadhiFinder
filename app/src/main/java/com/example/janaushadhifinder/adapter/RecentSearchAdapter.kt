package com.example.janaushadhifinder.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.janaushadhifinder.R

class RecentSearchAdapter(
    private val searches: List<String>,
    private val onSearchClick: (String) -> Unit
) : RecyclerView.Adapter<RecentSearchAdapter.RecentSearchViewHolder>() {

    class RecentSearchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val searchText: TextView = view.findViewById(R.id.tvSearchText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentSearchViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recent_search, parent, false)
        return RecentSearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecentSearchViewHolder, position: Int) {
        val search = searches[position]
        holder.searchText.text = search
        
        holder.itemView.setOnClickListener {
            onSearchClick(search)
        }
    }

    override fun getItemCount(): Int = searches.size
}
