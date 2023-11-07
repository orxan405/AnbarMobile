package com.nexis.anbar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class LogoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logo)

        supportActionBar?.hide()

        Handler().postDelayed({
            val intent = Intent(this@LogoActivity, MainActivity::class.java)
            startActivity(intent)
        }, 3000)
    }
}