package com.example.janaushadhifinder.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.janaushadhifinder.R

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var formContainer: LinearLayout
    private lateinit var summaryContainer: LinearLayout
    private lateinit var saveButton: Button
    private lateinit var editButton: ImageButton
    private lateinit var greetingText: TextView
    private lateinit var profileCompletionText: TextView
    private lateinit var profileNameText: TextView
    private lateinit var profileEmailText: TextView
    private lateinit var profilePhoneText: TextView
    private lateinit var profileAddressText: TextView
    private lateinit var profileAgeText: TextView
    private lateinit var profileGenderText: TextView
    private lateinit var totalSavingsText: TextView
    private lateinit var medicinesSearchedText: TextView
    private lateinit var thisMonthSavingsText: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = requireActivity().getSharedPreferences("UserData", Context.MODE_PRIVATE)

        formContainer = view.findViewById(R.id.formContainer)
        summaryContainer = view.findViewById(R.id.summaryContainer)
        saveButton = view.findViewById(R.id.btnSaveProfile)
        editButton = view.findViewById(R.id.btnEditProfile)
        greetingText = view.findViewById(R.id.tvGreeting)
        profileCompletionText = view.findViewById(R.id.tvProfileCompletion)
        profileNameText = view.findViewById(R.id.tvProfileName)
        profileEmailText = view.findViewById(R.id.tvProfileEmail)
        profilePhoneText = view.findViewById(R.id.tvProfilePhone)
        profileAddressText = view.findViewById(R.id.tvProfileAddress)
        profileAgeText = view.findViewById(R.id.tvProfileAge)
        profileGenderText = view.findViewById(R.id.tvProfileGender)
        totalSavingsText = view.findViewById(R.id.tvTotalSavings)
        medicinesSearchedText = view.findViewById(R.id.tvMedicinesSearched)
        thisMonthSavingsText = view.findViewById(R.id.tvThisMonthSavings)

        val nameField = view.findViewById<EditText>(R.id.etProfileName)
        val emailField = view.findViewById<EditText>(R.id.etProfileEmail)
        val phoneField = view.findViewById<EditText>(R.id.etProfilePhone)
        val addressField = view.findViewById<EditText>(R.id.etProfileAddress)
        val ageField = view.findViewById<EditText>(R.id.etProfileAge)
        val genderField = view.findViewById<EditText>(R.id.etProfileGender)

        fun updateGreeting() {
            val userName = sharedPref.getString("name", "")
            if (userName.isNullOrEmpty()) {
                greetingText.text = "Hi, there 👋"
            } else {
                greetingText.text = "Hi, $userName 👋"
            }
        }

        fun loadFields() {
            nameField.setText(sharedPref.getString("name", ""))
            emailField.setText(sharedPref.getString("email", ""))
            phoneField.setText(sharedPref.getString("phone", ""))
            addressField.setText(sharedPref.getString("address", ""))
            ageField.setText(sharedPref.getString("age", ""))
            genderField.setText(sharedPref.getString("gender", ""))
        }

        fun updateSavingsStats() {
            val totalSavings = sharedPref.getString("total_savings", "0")?.toInt() ?: 0
            val medicinesSearched = sharedPref.getString("medicines_searched", "0")?.toInt() ?: 0
            val thisMonthSavings = sharedPref.getString("this_month_savings", "0")?.toInt() ?: 0
            
            totalSavingsText.text = "₹$totalSavings"
            medicinesSearchedText.text = "$medicinesSearched"
            thisMonthSavingsText.text = "₹$thisMonthSavings"
        }

        fun updateProfileCompletion() {
            val name = sharedPref.getString("name", "")
            val email = sharedPref.getString("email", "")
            val phone = sharedPref.getString("phone", "")
            val address = sharedPref.getString("address", "")
            val age = sharedPref.getString("age", "")
            val gender = sharedPref.getString("gender", "")
            
            var completedFields = 0
            val totalFields = 6
            
            if (!name.isNullOrEmpty()) completedFields++
            if (!email.isNullOrEmpty()) completedFields++
            if (!phone.isNullOrEmpty()) completedFields++
            if (!address.isNullOrEmpty()) completedFields++
            if (!age.isNullOrEmpty()) completedFields++
            if (!gender.isNullOrEmpty()) completedFields++
            
            val percentage = (completedFields * 100) / totalFields
            profileCompletionText.text = "$percentage%"
        }

        fun refreshSummary() {
            val name = sharedPref.getString("name", "Not set")
            val email = sharedPref.getString("email", "Not set")
            val phone = sharedPref.getString("phone", "Not set")
            val address = sharedPref.getString("address", "Not set")
            val age = sharedPref.getString("age", "Not set")
            val gender = sharedPref.getString("gender", "Not set")

            profileNameText.text = name
            profileEmailText.text = email
            profilePhoneText.text = phone
            profileAddressText.text = address
            profileAgeText.text = age
            profileGenderText.text = gender
        }

        fun setEditing(active: Boolean) {
            formContainer.visibility = if (active) View.VISIBLE else View.GONE
            summaryContainer.visibility = if (active) View.GONE else View.VISIBLE
        }

        val hasSavedProfile = sharedPref.getString("name", "").isNullOrEmpty().not() &&
                sharedPref.getString("email", "").isNullOrEmpty().not()

        updateGreeting()
        updateSavingsStats()
        updateProfileCompletion()
        loadFields()
        refreshSummary()
        setEditing(!hasSavedProfile)

        editButton.setOnClickListener {
            setEditing(true)
            loadFields()
        }

        saveButton.setOnClickListener {
            val name = nameField.text.toString().trim()
            val email = emailField.text.toString().trim()
            val phone = phoneField.text.toString().trim()
            val address = addressField.text.toString().trim()
            val age = ageField.text.toString().trim()
            val gender = genderField.text.toString().trim()

            if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty()) {
                Toast.makeText(requireContext(), "Please complete all required fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            sharedPref.edit().apply {
                putString("name", name)
                putString("email", email)
                putString("phone", phone)
                putString("address", address)
                putString("age", age)
                putString("gender", gender)
                apply()
            }

            Toast.makeText(requireContext(), "Profile updated successfully", Toast.LENGTH_SHORT).show()
            updateGreeting()
            updateSavingsStats()
            updateProfileCompletion()
            refreshSummary()
            setEditing(false)
        }
    }
}