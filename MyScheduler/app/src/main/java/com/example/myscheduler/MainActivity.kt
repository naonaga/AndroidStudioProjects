package com.example.myscheduler

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import android.view.Menu
import android.view.MenuItem
import com.example.myscheduler.databinding.ActivityMainBinding
import com.example.myscheduler.databinding.ContentMainBinding
import io.realm.Realm
import io.realm.kotlin.where

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var contentBinding: ContentMainBinding
    private lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        realm = Realm.getDefaultInstance()
        val schedules = realm.where<Schedule>().findAll()
        contentBinding.listView.adapter = ScheduleAdapter(schedules)

        binding.fab.setOnClickListener {
            val intent = Intent(this, ScheduleEditActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

}