package com.hkgroups.rangolis.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.hkgroups.rangolis.fragments.HomeFragmentPage
import com.hkgroups.rangolis.R
import com.hkgroups.rangolis.fragments.VideosFragmentPage

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val homeFragment = HomeFragmentPage()
        val videoFragment = VideosFragmentPage()
        fragmentManager(homeFragment)
        val bottomNavigation : BottomNavigationView = findViewById(R.id.bottomNavigation)
        bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home_icon_id -> fragmentManager(homeFragment)
                R.id.video_icon_id -> fragmentManager(videoFragment)
                else -> fragmentManager(homeFragment)
            }
            true

        }
    }
    private fun fragmentManager(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.homeFrameLayout,fragment)
            commit()
        }
    }
}