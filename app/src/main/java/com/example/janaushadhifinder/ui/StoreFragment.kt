package com.example.janaushadhifinder.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.janaushadhifinder.R
import com.example.janaushadhifinder.adapter.StoreAdapter
import com.example.janaushadhifinder.data.StoreDataSource
import com.example.janaushadhifinder.data.Store

class StoreFragment : Fragment(R.layout.fragment_store) {

    private lateinit var allStores: List<Store>
    private lateinit var adapter: StoreAdapter
    private lateinit var openStoresCount: TextView
    private lateinit var nearbyStoresCount: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize data
        allStores = StoreDataSource.getStores()
        val openStores = StoreDataSource.getOpenStores()
        val nearbyStores = StoreDataSource.getNearbyStores("5.0 km")

        val recyclerView = view.findViewById<RecyclerView>(R.id.rvStores)
        val showAllBtn = view.findViewById<Button>(R.id.btnShowAllStores)
        val showOpenBtn = view.findViewById<Button>(R.id.btnShowOpenStores)
        val showNearbyBtn = view.findViewById<Button>(R.id.btnShowNearbyStores)
        openStoresCount = view.findViewById(R.id.tvOpenStoresCount)
        nearbyStoresCount = view.findViewById(R.id.tvNearbyStoresCount)

        adapter = StoreAdapter(allStores) { store, action ->
            when (action) {
                "call" -> callStore(store.phone)
                "navigate" -> navigateToStore(store.address)
                "directions" -> getDirections(store.address)
            }
        }

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        // Update statistics
        openStoresCount.text = "${openStores.size} stores open now"
        nearbyStoresCount.text = "${nearbyStores.size} stores within 5km"

        // Button click listeners
        showAllBtn.setOnClickListener {
            adapter.updateData(allStores)
        }

        showOpenBtn.setOnClickListener {
            adapter.updateData(openStores)
        }

        showNearbyBtn.setOnClickListener {
            adapter.updateData(nearbyStores)
        }
    }

    private fun callStore(phone: String) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phone")
        }
        startActivity(intent)
    }

    private fun navigateToStore(address: String) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse("geo:0,0?q=$address")
        }
        startActivity(intent)
    }

    private fun getDirections(address: String) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse("google.navigation:q=$address")
        }
        startActivity(intent)
    }
}