package com.example.myscheduler

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.realm.Realm
import com.example.myscheduler.databinding.ActivityScheduleEditBinding
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import java.lang.IllegalArgumentException
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date

class ScheduleEditActivity : AppCompatActivity() {
    private lateinit var realm: Realm
    private lateinit var binding: ActivityScheduleEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule_edit)
        realm = Realm.getDefaultInstance()
        binding.save.setOnClickListener {
            realm.executeTransaction {
                val maxId  = realm.where<Schedule>().max("id")
                val nextId = (maxId?.toLong() ?: 0L) + 1
                val schedule = realm.createObject<Schedule>(nextId)
                binding.dateEdit.text.toString().toDate("yyyy/MM/dd")?.let {
                    schedule.date = it
                }
                schedule.title = binding.titleEdit.text.toString()
                schedule.detail = binding.detailEdit.text.toString()
            }

            AlertDialog.Builder(this)
                .setMessage("Message")
                .setPositiveButton("OK", null)
                .show();
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

    fun String.toDate(pattern: String = "yyyy/MM/dd HH:mm") : Date? {
        val sdFormat = try {
            SimpleDateFormat(pattern)
        } catch (e: IllegalArgumentException) {
            null
        }
        val date = sdFormat?.let {
            try {
                it.parse(this)
            } catch (e: ParseException) {
                null
            }
        }
        return date
    }
}