package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class StoryDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story_details)
        val text:String = intent.getStringExtra("user").toString()
        val image = intent.getStringExtra("image")
        //val view = findViewById<TextView>(R.id.txtDetail)
        val imageId = findViewById<ImageView>(R.id.imageStoryDetail)
        //view.text = text
        Glide.with(this)
            .load(image)
            .into(imageId)

    }
}