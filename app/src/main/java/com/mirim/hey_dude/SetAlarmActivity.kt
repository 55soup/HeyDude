package com.mirim.hey_dude

import android.content.Context
import android.content.Intent
import android.icu.number.Scale.none
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hey_dude.R

class SetAlarmActivity : AppCompatActivity() {

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, SetAlarmActivity::class.java)
        }

    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_alarm)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        
        overridePendingTransition(R.anim.slide_up_enter, R.anim.slide_down_exit)
    }
}