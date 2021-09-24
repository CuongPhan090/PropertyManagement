package com.example.propertymanagement

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.example.propertymanagement.databinding.ActivityMenuTestBinding

class MenuTestActivity : AppCompatActivity() {
    lateinit var binding: ActivityMenuTestBinding
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title= "Settings"
        actionBarDrawerToggle = ActionBarDrawerToggle(this,
            binding.settingsDrawerLayout, R.string.open, R.string.close
        )
        actionBarDrawerToggle.syncState()
       supportActionBar?.setDisplayHomeAsUpEnabled(true)


        binding.SettingsNavMenu.setNavigationItemSelectedListener {
                menuItem ->
            when (menuItem.itemId) {
                R.id.myProfile -> {

                    Toast.makeText(baseContext, "My Profile Selected", Toast.LENGTH_LONG).show()

                }

                R.id.appSettings -> {

                    Toast.makeText(baseContext, "App Settings Selected", Toast.LENGTH_LONG).show()

                }

                R.id.alertPreferences -> {
                    Toast.makeText(baseContext, "Pref Selected", Toast.LENGTH_LONG).show()

                }
                R.id.security -> {
                    Toast.makeText(baseContext, "Security Selected", Toast.LENGTH_LONG).show()

                }





            }
            binding.settingsDrawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }
}