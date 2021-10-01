package org.sussanacode.propertymanagement.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.sussanacode.propertymanagement.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {
    lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setUpEvents()

    }

    private fun setUpEvents() {
//       binding.addProperty.setOnClickListener {
//           startActivity(Intent(baseContext, PropertyActivity::class.java))
//       }

        binding.addProperty.setOnClickListener {
            startActivity(Intent(baseContext, PropertyByUserActivity::class.java))
        }

//        binding.addProperty.setOnClickListener {
//            startActivity(Intent(baseContext, AddPropertyActivity::class.java))
//        }
    }
}