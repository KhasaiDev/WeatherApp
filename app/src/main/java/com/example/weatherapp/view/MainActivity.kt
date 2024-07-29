package com.example.weatherapp.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.weatherapp.R
import com.example.weatherapp.databinding.MainActivityBinding
import com.example.weatherapp.view.fragments.DetailFragment
import com.example.weatherapp.view.fragments.SettingsFragment


class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainActivityBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setSupportActionBar(binding.toolbar)
        setContentView(binding.root)
        val navHostFragment=supportFragmentManager.findFragmentById(R.id.nav_host_Fragment) as NavHostFragment
        navController=navHostFragment.navController
        setupActionBarWithNavController(navController)
        setupDestinationChangedListener()

    }
    private fun setupDestinationChangedListener() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> {
                    supportActionBar?.hide()
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                    supportActionBar?.setDisplayShowHomeEnabled(false)
                }
                R.id.detailFragment -> {
                    supportActionBar?.show()
                    supportActionBar?.setDisplayHomeAsUpEnabled(true)
                    supportActionBar?.setDisplayShowHomeEnabled(true)
                }
                R.id.settingsFragment -> {
                    supportActionBar?.show()
                    supportActionBar?.setDisplayHomeAsUpEnabled(true)
                    supportActionBar?.setDisplayShowHomeEnabled(true)
                }
                else -> {
                    supportActionBar?.hide()
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                    supportActionBar?.setDisplayShowHomeEnabled(false)
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        super.onPrepareOptionsMenu(menu)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.nav_host_Fragment)
        menu?.findItem(R.id.configMenu)?.isVisible = currentFragment !is SettingsFragment

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.nav_host_Fragment)
        return when (item.itemId) {
            R.id.configMenu -> {
                val currentDestination = navController.currentDestination
                if (currentDestination?.id != R.id.settingsFragment) {
                    navController.navigate(R.id.action_detailFragment_to_settingsFragment)
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
