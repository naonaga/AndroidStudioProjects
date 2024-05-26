package com.example.myslidesshow

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView

import com.example.myslidesshow.databinding.ActivityMainBinding
import kotlin.concurrent.timer

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val resources = listOf(
        R.drawable.octr, R.drawable.octr2,
        R.drawable.rafuyasu, R.drawable.temecri,
        R.drawable.temecri2, R.drawable.temenosu,
        R.drawable.temenosu2, R.drawable.temenosu3,
        R.drawable.temenosu4, R.drawable.temenosu6,
    )

    private var position = 0
    private var isSlideshow = false
    private val handler = Handler()
    private lateinit var player: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageSwitcher.setFactory {
            ImageView(this)
        }
        binding.imageSwitcher.setImageResource(resources[0])

        binding.prevButton.setOnClickListener {
            binding.imageSwitcher.setInAnimation(this, android.R.anim.fade_in)
            binding.imageSwitcher.setOutAnimation(this, android.R.anim.fade_out)
            movePosition(-1)
        }
        binding.nextButton.setOnClickListener {
            binding.imageSwitcher.setInAnimation(this, android.R.anim.slide_in_left)
            binding.imageSwitcher.setOutAnimation(this, android.R.anim.slide_out_right)
            movePosition(1) }
        timer(period = 5000) {
            handler.post {
                if (isSlideshow) movePosition(1)
            }
        }

        binding.slideshowButton.setOnClickListener {
            isSlideshow = !isSlideshow

            when (isSlideshow) {
                true -> player.start()
                false -> player.apply {
                    pause()
                    seekTo(0)
                }
            }
        }

        player = MediaPlayer.create(this, R.raw.getdown)
        player.isLooping = true
    }

    private fun movePosition(move: Int) {
        position += move
        if (position >= resources.size) {
            position = 0
        } else if ( position < 0) {
            position = resources.size - 1
        }
        binding.imageSwitcher.setImageResource(resources[position])
    }
}