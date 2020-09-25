package com.vaulert.ui_motionlayout

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_motionlayout.*

class MotionLayoutActivity :AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_motionlayout)

        btn.setOnClickListener {
            startActivity(Intent(this,LoginActivity::class.java))
        }
    }

}