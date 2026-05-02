package com.example.janaushadhifinder.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.janaushadhifinder.R
import com.example.janaushadhifinder.data.Store

class StoreAdapter(
    private var stores: List<Store>,
    private val onActionClick: (Store, String) -> Unit
) : RecyclerView.Adapter<StoreAdapter.StoreViewHolder>() {

    class StoreViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.tvStoreName)
        val address: TextView = view.findViewById(R.id.tvStoreAddress)
        val phone: TextView = view.findViewById(R.id.tvStorePhone)
        val distance: TextView = view.findViewById(R.id.tvStoreDistance)
        val status: TextView = view.findViewById(R.id.tvStoreStatus)
        val rating: RatingBar = view.findViewById(R.id.rbStoreRating)
        val callBtn: Button = view.findViewById(R.id.btnCallStore)
        val navigateBtn: Button = view.findViewById(R.id.btnNavigateStore)
        val directionsBtn: Button = view.findViewById(R.id.btnGetDirections)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_store, parent, false)
        return StoreViewHolder(view)
    }

    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        val store = stores[position]

        holder.name.text = store.name
        holder.address.text = "📍 ${store.address}"
        holder.phone.text = "📞 ${store.phone}"
        holder.distance.text = "📏 ${store.distance}"
        holder.rating.rating = store.rating

        // Status styling
        if (store.isOpen) {
            holder.status.text = "🟢 OPEN NOW"
            holder.status.setTextColor(holder.itemView.context.getColor(R.color.success_green))
        } else {
            holder.status.text = "🔴 CLOSED"
            holder.status.setTextColor(holder.itemView.context.getColor(R.color.warning_orange))
        }

        // Click listeners
        holder.callBtn.setOnClickListener {
            onActionClick(store, "call")
        }

        holder.navigateBtn.setOnClickListener {
            onActionClick(store, "navigate")
        }

        holder.directionsBtn.setOnClickListener {
            onActionClick(store, "directions")
        }
    }

    override fun getItemCount(): Int = stores.size

    fun updateData(newList: List<Store>) {
        stores = newList
        notifyDataSetChanged()
    }
}
