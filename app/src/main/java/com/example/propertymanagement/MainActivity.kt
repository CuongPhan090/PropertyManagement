package com.example.propertymanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import com.example.propertymanagement.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    val LAUNCH_LOGIN_SCREEN = 100
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

//        if(LAUNCH_HOME_SCREEN != 0){
//            handler.sendEmptyMessageDelayed(LAUNCH_HOME_SCREEN, screentimeout)
//
//        }else if(LAUNCH_LOGIN_SCREEN != 0){
//            handler.sendEmptyMessageDelayed(LAUNCH_LOGIN_SCREEN, screentimeout)
//        }

    }

    val handler = object : Handler(){
        override fun handleMessage(msg: Message){
            super.handleMessage(msg)

            if (msg.what == LAUNCH_HOME_SCREEN){
                startActivity(Intent(baseContext, DashboardActivity::class.java))
                finish()
            }else if(msg.what == LAUNCH_LOGIN_SCREEN){
                startActivity(Intent(baseContext, LoginActivity::class.java))
                finish()
            }
        }
    }

}