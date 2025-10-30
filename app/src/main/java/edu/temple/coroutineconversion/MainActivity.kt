package edu.temple.coroutineconversion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale
import kotlin.coroutines.coroutineContext

class MainActivity : AppCompatActivity() {

    //TODO (Refactor to replace Thread code with coroutines)
    //replace handler


    private val cakeImageView: ImageView by lazy {
        findViewById(R.id.imageView)
    }

    private val currentTextView: TextView by lazy {
        findViewById(R.id.currentTextView)
    }



    fun startCoroutineTask() {
        CoroutineScope(Dispatchers.Default).launch {
            repeat(100) { index ->

                withContext(Dispatchers.Main) {
                    currentTextView.text = String.format(Locale.getDefault(), "Current opacity: %d", index)
                    cakeImageView.alpha = index / 100f
                }
                delay(40) // Non-blocking delay
            }
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        findViewById<Button>(R.id.revealButton).setOnClickListener {
            GlobalScope.launch { startCoroutineTask() }
        }
    }
}