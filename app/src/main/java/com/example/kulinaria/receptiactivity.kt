package com.example.kulinaria

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.gamedrive.R

class receptiactivity : AppCompatActivity() {
    private lateinit var ImageView: ImageView
    private lateinit var TitleTextView: TextView
    private lateinit var recepti: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.second_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.second)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        ImageView = findViewById<ImageView>(R.id.imageView)
        TitleTextView = findViewById<TextView>(R.id.textView)
        recepti = findViewById<TextView>(R.id.textView2)


        TitleTextView.setText(intent.getStringExtra("saxeli"))
        recepti.setText(intent.getStringExtra("recepti"))

//        Picasso.get()
//            .load(intent.getStringExtra("IMAGE"))
//            .resize(800, 1100)
//            .into(ImageView);
    }
}
