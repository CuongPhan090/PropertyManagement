package org.sussanacode.propertymanagement.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import org.sussanacode.propertymanagement.R
import org.sussanacode.propertymanagement.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    val LAUNCH_LOGIN_SCREEN = 200
    val LAUNCH_HOME_SCREEN = 200

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getwaitingTime()
    }

    private fun getwaitingTime() {
        val screentimeout = 3000L

        handler.sendEmptyMessageDelayed(LAUNCH_HOME_SCREEN, screentimeout)
    }

    val handler = object : Handler(){
        override fun handleMessage(msg: Message){
            super.handleMessage(msg)

            if (msg.what == LAUNCH_HOME_SCREEN){
                startActivity(Intent(baseContext, DashboardActivity::class.java))
                finish()
            }
        }
    }

}