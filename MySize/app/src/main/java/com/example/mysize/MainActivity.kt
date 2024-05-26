package com.example.mysize

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mysize.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pref = getSharedPreferences("com.example.mysize.preferences", MODE_PRIVATE)
        pref.apply {
            val editNeck = getString("NECK", "")
            val editSleeve = getString("SLEEVE", "")
            val editWasit = getString("WAIST", "")
            val editInseam = getString("INSEAM", "")

            binding.neck.setText(editNeck)
            binding.sleeve.setText(editSleeve)
            binding.waist.setText(editWasit)
            binding.inseam.setText(editInseam)
        }

        binding.save.setOnClickListener{ onSaveTapped() }
        binding.heightButton.setOnClickListener {
            val intent = Intent(this, HeightActivity::class.java)
            startActivity(intent)
        }
    }
    private fun onSaveTapped() {
        val pref = getSharedPreferences("com.example.mysize.preferences", MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString("NECK", binding.neck.text.toString())
              .putString("SLEEVE", binding.sleeve.text.toString())
              .putString("WAIST", binding.waist.text.toString())
              .putString("INSEAM", binding.inseam.text.toString())
              .apply()
    }
}