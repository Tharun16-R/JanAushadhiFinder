package com.example.janaushadhifinder

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.janaushadhifinder.ui.HomeFragment
import com.example.janaushadhifinder.ui.ProfileFragment
import com.example.janaushadhifinder.ui.SearchFragment
import com.example.janaushadhifinder.ui.StoreFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)

        if (savedInstanceState == null) {
            loadFragment(HomeFragment())
        }

        bottomNav.setOnItemSelectedListener { item ->
            val fragment = when (item.itemId) {
                R.id.home -> HomeFragment()
                R.id.search -> SearchFragment()
                R.id.stores -> StoreFragment()
                R.id.profile -> ProfileFragment()
                else -> HomeFragment()
            }

            loadFragment(fragment)
            true
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }
}