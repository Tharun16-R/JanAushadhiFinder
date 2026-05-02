package com.example.janaushadhifinder.ui

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.janaushadhifinder.R
import com.example.janaushadhifinder.adapter.RecentSearchAdapter
import com.example.janaushadhifinder.adapter.TopSavingsAdapter
import com.example.janaushadhifinder.data.MedicineDataSource
import com.example.janaushadhifinder.model.Medicine
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var greetingText: TextView
    private lateinit var savingsCard: TextView
    private lateinit var quickActionsLayout: LinearLayout
    private lateinit var topSavingsRecyclerView: RecyclerView
    private lateinit var recentSearchesRecyclerView: RecyclerView
    private lateinit var didYouKnowText: TextView
    
    private val didYouKnowMessages = listOf(
        "� Generic medicines can save up to 80% compared to branded medicines!",
        "� Jan Aushadhi stores offer quality medicines at affordable prices.",
        "� Switching to generics can save thousands yearly on healthcare costs.",
        "📋 Ask your doctor about generic alternatives for your prescriptions.",
        "🏪 All generic medicines have the same active ingredients as branded ones."
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        initializeViews(view)
        setupGreeting()
        setupSavingsCard()
        setupQuickActions()
        setupTopSavings()
        setupRecentSearches()
        setupDidYouKnow()
    }

    private fun initializeViews(view: View) {
        greetingText = view.findViewById(R.id.tvGreeting)
        savingsCard = view.findViewById(R.id.tvSavingsCard)
        quickActionsLayout = view.findViewById(R.id.quickActionsLayout)
        topSavingsRecyclerView = view.findViewById(R.id.rvTopSavings)
        recentSearchesRecyclerView = view.findViewById(R.id.rvRecentSearches)
        didYouKnowText = view.findViewById(R.id.tvDidYouKnow)
    }

    private fun setupGreeting() {
        val sharedPref = requireActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE)
        val userName = sharedPref.getString("name", "User") ?: "User"
        
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val greeting = when (hour) {
            in 0..11 -> "Good Morning"
            in 12..17 -> "Good Afternoon"
            else -> "Good Evening"
        }
        
        greetingText.text = "Hello, $userName"
    }

    private fun setupSavingsCard() {
        val sharedPref = requireActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE)
        val thisMonthSavings = sharedPref.getString("this_month_savings", "0")?.toInt() ?: 0
        savingsCard.text = "You saved ₹$thisMonthSavings this month"
    }

    private fun setupQuickActions() {
        // Search Button
        val searchBtn = Button(requireContext()).apply {
            text = "🔍 Search"
            setTextColor(requireContext().getColor(R.color.white))
            setBackgroundColor(requireContext().getColor(R.color.primary_blue))
            setPadding(0, 48, 0, 48)
            layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
            setOnClickListener { navigateToTab(1) }
        }
        
        // Stores Button
        val storesBtn = Button(requireContext()).apply {
            text = "📍 Stores"
            setTextColor(requireContext().getColor(R.color.white))
            setBackgroundColor(requireContext().getColor(R.color.accent_green))
            setPadding(0, 48, 0, 48)
            layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f).apply {
                marginStart = 8
                marginEnd = 8
            }
            setOnClickListener { navigateToTab(2) }
        }
        
        // My Medicines Button
        val myMedicinesBtn = Button(requireContext()).apply {
            text = "💊 My Medicines"
            setTextColor(requireContext().getColor(R.color.white))
            setBackgroundColor(requireContext().getColor(R.color.accent_purple))
            setPadding(0, 48, 0, 48)
            layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
            setOnClickListener { navigateToTab(1) } // Navigate to Search with cart items
        }
        
        quickActionsLayout.removeAllViews()
        quickActionsLayout.addView(searchBtn)
        quickActionsLayout.addView(storesBtn)
        quickActionsLayout.addView(myMedicinesBtn)
    }

    private fun setupTopSavings() {
        val topSavings = MedicineDataSource.getTopSavingsMedicines()
        val adapter = TopSavingsAdapter(topSavings) { medicine ->
            showMedicineDetails(medicine)
        }
        
        topSavingsRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        topSavingsRecyclerView.adapter = adapter
    }

    private fun setupRecentSearches() {
        val sharedPref = requireActivity().getSharedPreferences("AppData", Context.MODE_PRIVATE)
        val recentSearches = getRecentSearches(sharedPref)
        
        if (recentSearches.isNotEmpty()) {
            val adapter = RecentSearchAdapter(recentSearches) { query ->
                navigateToTab(1, query)
            }
            
            recentSearchesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            recentSearchesRecyclerView.adapter = adapter
        }
    }

    private fun setupDidYouKnow() {
        val randomMessage = didYouKnowMessages.random()
        didYouKnowText.text = randomMessage
        
        // Rotate message every 15 seconds
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed(object : Runnable {
            override fun run() {
                val newMessage = didYouKnowMessages.random()
                didYouKnowText.text = newMessage
                handler.postDelayed(this, 15000)
            }
        }, 15000)
    }

    private fun getRecentSearches(sharedPref: android.content.SharedPreferences): List<String> {
        val searchesSet = sharedPref.getStringSet("recent_searches", emptySet()) ?: emptySet()
        return searchesSet.toList().takeLast(5).reversed()
    }

    private fun saveRecentSearch(query: String) {
        val sharedPref = requireActivity().getSharedPreferences("AppData", Context.MODE_PRIVATE)
        val searchesSet = sharedPref.getStringSet("recent_searches", emptySet())?.toMutableSet() ?: mutableSetOf()
        searchesSet.add(query)
        
        // Keep only last 10 searches
        if (searchesSet.size > 10) {
            val toRemove = searchesSet.take(searchesSet.size - 10)
            searchesSet.removeAll(toRemove)
        }
        
        sharedPref.edit().putStringSet("recent_searches", searchesSet).apply()
    }

    private fun showMedicineDetails(medicine: Medicine) {
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        val view = LayoutInflater.from(requireContext()).inflate(R.layout.bottom_sheet_medicine_details, null)
        
        // Setup medicine details
        view.findViewById<TextView>(R.id.tvBrandName).text = medicine.brandName
        view.findViewById<TextView>(R.id.tvGenericName).text = "→ ${medicine.genericName}"
        view.findViewById<TextView>(R.id.tvCategory).text = medicine.category
        view.findViewById<TextView>(R.id.tvBrandPrice).text = "₹${medicine.brandPrice}"
        view.findViewById<TextView>(R.id.tvGenericPrice).text = "₹${medicine.genericPrice}"
        
        val savingsAmount = medicine.getSavingsAmount()
        val savingsPercentage = medicine.getSavingsPercentage()
        view.findViewById<TextView>(R.id.tvSavings).text = "Save ₹$savingsAmount ($savingsPercentage%)"
        
        // Buttons
        view.findViewById<Button>(R.id.btnAddToCart).setOnClickListener {
            // Add to cart logic (would need to communicate with SearchFragment)
            bottomSheetDialog.dismiss()
        }
        
        view.findViewById<Button>(R.id.btnSetReminder).setOnClickListener {
            // Set reminder logic
            bottomSheetDialog.dismiss()
        }
        
        bottomSheetDialog.setContentView(view)
        bottomSheetDialog.show()
    }

    private fun navigateToTab(tabIndex: Int, query: String = "") {
        val mainActivity = requireActivity()
        if (mainActivity is com.example.janaushadhifinder.MainActivity) {
            try {
                val bottomNav = mainActivity.findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(R.id.bottomNav)
                bottomNav.selectedItemId = when (tabIndex) {
                    1 -> R.id.search
                    2 -> R.id.stores
                    else -> R.id.home
                }
                
                // Store query for SearchFragment
                if (query.isNotEmpty() && tabIndex == 1) {
                    val sharedPref = mainActivity.getSharedPreferences("AppData", Context.MODE_PRIVATE)
                    sharedPref.edit().putString("search_query", query).apply()
                }
            } catch (e: Exception) {
                // Fallback
            }
        }
    }
}