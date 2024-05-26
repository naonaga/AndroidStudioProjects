package com.example.mysize

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.SeekBar
import com.example.mysize.databinding.ActivityHeightBinding

private lateinit var binding: ActivityHeightBinding

class HeightActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHeightBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.spinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override
                fun onItemSelected(parent: AdapterView<*>?,
                                   view: View?,
                                   position: Int,
                                   id: Long) {
                    val spinner = parent as? Spinner
                    val item = spinner?.selectedItem as? String
                    item?.let {
                        if (it.isNotEmpty() ) binding.height.text = it
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
        getSharedPreferences("com.example.mysize.preferences", MODE_PRIVATE).apply {
            val heightVal = getInt("HEIGHT", 160)
            binding.height.setText(heightVal.toString())
            binding.seekBar.progress = heightVal
        }

        binding.seekBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar?,
                                               progress: Int,
                                               fromUser: Boolean) {
                    binding.height.text = progress.toString()
                }

                override
                fun onStartTrackingTouch(seekBar: SeekBar?) {}

                override
                fun onStopTrackingTouch(seekBar: SeekBar?) {}

            }
        )

        binding.radioGroup.setOnCheckedChangeListener {
            group, checkedId ->
                binding.height.text = findViewById<RadioButton>(checkedId).text
        }
    }
    override fun onPause() {
        super.onPause()
        getSharedPreferences("com.example.mysize.preferences", MODE_PRIVATE).edit()
            .putInt("HEIGHT", binding.height.text.toString().toInt())
            .apply()
    }
}
