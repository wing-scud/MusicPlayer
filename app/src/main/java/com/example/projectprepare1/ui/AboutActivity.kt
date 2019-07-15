package com.example.projectprepare1.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.example.projectprepare1.R

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_about)
        val actionbar = supportActionBar
        val intent = intent
        var temp = intent.getIntExtra("temp",0)
        actionbar?.hide()
        when(temp){
            0->{
            }
            1-> {
            }
            2-> {

            }
        }

    }
}
