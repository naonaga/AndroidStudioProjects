package com.example.janken

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.janken.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.gu.setOnClickListener { onJankenButtonTapped(it) }
        binding.choki.setOnClickListener { onJankenButtonTapped(it) }
        binding.pa.setOnClickListener { onJankenButtonTapped(it) }

        val pref = getSharedPreferences("com.example.preferences", MODE_PRIVATE)
        val editor = pref.edit()
        editor.clear().apply()
    }

    fun onJankenButtonTapped(view: View?) {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("MY_HAND", view?.id)
        startActivity(intent)
    }

}