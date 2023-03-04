package com.example.agendaapp

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.agendaapp.databinding.ActivityMainBinding
import com.example.agendaapp.ui.SettingsFragment
import com.example.agendaapp.ui.detailedView.AddNewContactFragment
import com.example.agendaapp.ui.detailedView.DetailedContactFragment
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
        addSettingsButtonListener()
    }

    private fun addSettingsButtonListener() {
        binding.settingsButton.setOnClickListener {
            binding.toolbar.visibility = View.GONE
            binding.navView.visibility = View.GONE
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.main_layout,
                    SettingsFragment(),
                    "SETTINGS_FRAGMENT"
                )
                .addToBackStack("DETAILED_CONTACT_FRAGMENT")
                .commit()
        }
    }

    private fun whenBackButtonClickedReturnToAgendaFragment() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

                val currentFragment =
                    supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main2)
                if (currentFragment?.childFragmentManager?.fragments?.get(0) is DetailedContactFragment
                    || currentFragment?.childFragmentManager?.fragments?.get(0) is AddNewContactFragment) {
                    binding.navView.visibility = View.VISIBLE
                    binding.toolbar.visibility = View.VISIBLE
                }
                supportFragmentManager.popBackStack()
            }
        })
    }

}