package com.example.propertymanagement.ui.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.example.propertymanagement.R
import com.example.propertymanagement.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {
    lateinit var binding: ActivityDashboardBinding
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        actionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            R.string.open,
            R.string.close
        )
        actionBarDrawerToggle.syncState()

        binding.navView.setNavigationItemSelectedListener{ itemMenu ->
            when (itemMenu.itemId) {
                R.id.myProfile -> {
                    Toast.makeText(baseContext, "My Profile", Toast.LENGTH_SHORT).show()
                }

                R.id.appSettings -> {
                    Toast.makeText(baseContext, "App settings", Toast.LENGTH_SHORT).show()
                }

                R.id.alertPreferences -> {
                    Toast.makeText(baseContext, "Alert Preference", Toast.LENGTH_SHORT).show()
                }

                R.id.security -> {
                    Toast.makeText(baseContext, "Security", Toast.LENGTH_SHORT).show()
                }

                R.id.myCurrentPlan -> {
                    Toast.makeText(baseContext, "Current Plan", Toast.LENGTH_SHORT).show()
                }

                R.id.paymentDetails -> {
                    Toast.makeText(baseContext, "Payment Details", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(baseContext, "Nothing selected", Toast.LENGTH_SHORT).show()
                }
            }
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            finishAffinity()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_bar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}