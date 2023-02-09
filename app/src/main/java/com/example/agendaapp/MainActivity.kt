package com.example.agendaapp

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.agendaapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main2)
        navView.setupWithNavController(navController)

        whenBackButtonClickedReturnToAgendaFragment()
    }


    private fun whenBackButtonClickedReturnToAgendaFragment() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (isOnDetailedContactsFragment()) {
                    supportFragmentManager.popBackStack()
                }
                binding.navView.visibility = View.VISIBLE
                binding.toolbar.visibility = View.VISIBLE
            }
        })
    }

    private fun isOnDetailedContactsFragment(): Boolean {
        return binding.navView.visibility == View.GONE
    }
}