package com.example.janaushadhifinder.ui

import android.content.Context
import android.os.Bundle
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.janaushadhifinder.R
import com.example.janaushadhifinder.adapter.MedicineAdapter
import com.example.janaushadhifinder.data.MedicineDataSource
import com.example.janaushadhifinder.model.Medicine

class SearchFragment : Fragment(R.layout.fragment_search) {

    private lateinit var allMedicines: List<Medicine>
    private lateinit var adapter: MedicineAdapter
    private lateinit var cartItems: MutableList<Medicine>
    private lateinit var savingsSummary: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize data
        allMedicines = MedicineDataSource.getMedicines()
        cartItems = mutableListOf()

        val searchInput = view.findViewById<EditText>(R.id.etMedicineSearch)
        val searchButton = view.findViewById<Button>(R.id.btnSearchMedicine)
        val recyclerView = view.findViewById<RecyclerView>(R.id.rvMedicines)
        val clearCartButton = view.findViewById<Button>(R.id.btnClearCart)
        savingsSummary = view.findViewById(R.id.tvSavingsSummary)

        adapter = MedicineAdapter(allMedicines, cartItems) { medicine, action ->
            when (action) {
                "add_to_cart" -> addToCart(medicine)
                "remove_from_cart" -> removeFromCart(medicine)
                "check_availability" -> checkAvailability(medicine)
                "set_reminder" -> setReminder(medicine)
            }
        }

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        searchButton.setOnClickListener {
            val query = searchInput.text.toString().trim()
            performSmartSearch(query)
            saveRecentSearch(query)
        }

        // Enable real-time search as user types
        searchInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                performSmartSearch(s.toString().trim())
            }
            override fun afterTextChanged(s: android.text.Editable?) {}
        })

        clearCartButton.setOnClickListener {
            clearCart()
        }

        // Check for initial query from HomeFragment
        val sharedPref = requireActivity().getSharedPreferences("AppData", Context.MODE_PRIVATE)
        val initialQuery = sharedPref.getString("search_query", "")
        if (!initialQuery.isNullOrEmpty()) {
            searchInput.setText(initialQuery)
            performSmartSearch(initialQuery)
            // Clear the stored query after using it
            sharedPref.edit().remove("search_query").apply()
        } else {
            // Show popular medicines when no initial query
            showPopularMedicines()
            updateSavingsSummary()
        }
    }

    private fun showPopularMedicines() {
        // Show top 5 medicines by savings
        val popularMedicines = allMedicines
            .sortedByDescending { it.getSavingsAmount() }
            .take(5)
        
        adapter.updateData(popularMedicines)
        savingsSummary.text = "💊 Popular Medicines - Top Savings Today!"
    }

    private fun performSmartSearch(query: String) {
        if (query.isEmpty()) {
            adapter.updateData(allMedicines)
            return
        }

        val filtered = allMedicines.filter { medicine ->
            val queryLower = query.lowercase()
            
            // Smart search: Check brand name, generic name, and category
            medicine.brandName.lowercase().contains(queryLower) ||
            medicine.genericName.lowercase().contains(queryLower) ||
            medicine.category.lowercase().contains(queryLower) ||
            
            // Fuzzy matching: Check if query contains parts of names
            medicine.brandName.lowercase().split(" ", "-").any { part ->
                part.startsWith(queryLower) || queryLower.startsWith(part)
            } ||
            medicine.genericName.lowercase().split(" ", "-").any { part ->
                part.startsWith(queryLower) || queryLower.startsWith(part)
            }
        }

        adapter.updateData(filtered)
    }

    private fun addToCart(medicine: Medicine) {
        if (!cartItems.any { it.brandName == medicine.brandName }) {
            cartItems.add(medicine.copy(inCart = true))
            updateSavingsSummary()
            adapter.notifyDataSetChanged()
        }
    }

    private fun removeFromCart(medicine: Medicine) {
        cartItems.removeAll { it.brandName == medicine.brandName }
        updateSavingsSummary()
        adapter.notifyDataSetChanged()
    }

    private fun clearCart() {
        cartItems.clear()
        updateSavingsSummary()
        adapter.notifyDataSetChanged()
    }

    private fun updateSavingsSummary() {
        if (cartItems.isEmpty()) {
            savingsSummary.text = "No items in cart"
            return
        }

        val totalBranded = cartItems.sumOf { it.brandPrice }
        val totalGeneric = cartItems.sumOf { it.genericPrice }
        val totalSavings = totalBranded - totalGeneric
        val savingsPercentage = if (totalBranded > 0) (totalSavings * 100) / totalBranded else 0

        savingsSummary.text = "Cart: ${cartItems.size} items | Branded: ₹$totalBranded | Generic: ₹$totalGeneric | You Save: ₹$totalSavings ($savingsPercentage%)"
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

    private fun showWelcomeMessage() {
        // Show initial message about savings potential
        val totalPotentialSavings = allMedicines.take(10).sumOf { it.getSavingsAmount() }
        val message = "💰 Save up to ₹$totalPotentialSavings on just 10 medicines! Search for your branded medicine to see savings."
        savingsSummary.text = message
    }

    private fun checkAvailability(medicine: Medicine) {
        // Simulate availability check
        val isAvailable = (1..10).random() > 3 // 70% chance of availability
        val message = if (isAvailable) {
            "✅ ${medicine.brandName} is available in nearby Jan Aushadhi stores!"
        } else {
            "❌ ${medicine.brandName} is currently out of stock. Try again later."
        }
        
        // Show snackbar or dialog
        android.widget.Toast.makeText(requireContext(), message, android.widget.Toast.LENGTH_LONG).show()
    }

    private fun setReminder(medicine: Medicine) {
        showReminderDialog(medicine)
    }

    private fun showReminderDialog(medicine: Medicine) {
        val options = arrayOf("Daily Reminder", "Monthly Refill", "Cancel Reminders")
        
        androidx.appcompat.app.AlertDialog.Builder(requireContext())
            .setTitle("Set Reminder for ${medicine.brandName}")
            .setItems(options) { _, which ->
                when (which) {
                    0 -> {
                        val reminderManager = com.example.janaushadhifinder.utils.ReminderManager(requireContext())
                        reminderManager.setDailyReminder(medicine)
                    }
                    1 -> {
                        val reminderManager = com.example.janaushadhifinder.utils.ReminderManager(requireContext())
                        reminderManager.setMonthlyRefillReminder(medicine)
                    }
                    2 -> {
                        val reminderManager = com.example.janaushadhifinder.utils.ReminderManager(requireContext())
                        reminderManager.cancelReminder(medicine)
                    }
                }
            }
            .show()
    }
}