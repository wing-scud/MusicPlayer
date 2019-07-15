package com.example.projectprepare1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_about.*
import kotlinx.android.synthetic.main.fragment_song_in_list.*
import kotlinx.android.synthetic.main.nav_header_main.*

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
//        next.setOnClickListener({
//            val intent = Intent(this, About2Activity::class.java)
//            startActivity(intent);
//        })
    }
}
